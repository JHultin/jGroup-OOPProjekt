package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.m2phyInterfaces.ICollisionListener;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yen on 2015-05-02.
 */
public class CollisionListener implements ContactListener, ICollisionListener {

    private final List<IPickupable> itemsToRemove;
    private String newState;

    private final List<IEnemy> enemyToChangeDirection;
    private final List<IBullet> bulletsToRemove;

    private final List<Fixture> fixtureList;

    public CollisionListener(){
        itemsToRemove = new ArrayList<IPickupable>();
        enemyToChangeDirection = new ArrayList<IEnemy>();
        bulletsToRemove = new ArrayList<IBullet>();
        fixtureList = new ArrayList<Fixture>();

    }

    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();
        fixtureList.add(fa);
        fixtureList.add(fb);

        //Handles if character on ground
        for(int i=0; i<2; i++) {
            if (isCorrectFixtureType(fixtureList.get(i), "footSensor")) {
                ((ICharacter) fixtureList.get(i).getBody().getUserData()).hitGround();
            }
            if (isCorrectFixtureType(fixtureList.get(i), "food")) {
                itemsToRemove.add((IFood) (fixtureList.get(i).getBody().getUserData()));
            }
            if (isCorrectFixtureType(fixtureList.get(i), "pickupCharacter")) {
                itemsToRemove.add((IPickupableCharacter) (fixtureList.get(i).getBody().getUserData()));
            }
            if (isCorrectFixtureType(fixtureList.get(i), "finish")) {
                this.newState = "victory";
            }
            if (isCorrectFixtureType(fixtureList.get(i), "jumpPointSensor")) {
                ((ICharacter) fixtureList.get(1-i).getBody().getUserData()).toggleFollowerOnJumpPoint();
            }
            //When enemy walks into a body or a wall into change direction
            if (isCorrectFixtureType(fixtureList.get(i), "enemyUpperSensor")) {
                handlesEnemyCollision(fixtureList.get(i), fixtureList.get(1-i));
            }
            //When bullet hits enemy it takes damage
            if ("bullet".equals(fixtureList.get(i).getUserData())) {
                if ("enemyUpperSensor".equals(fixtureList.get(1-i).getUserData())) {
                    ((IEnemy) fixtureList.get(1-i).getBody().getUserData()).decHP(((IBullet) (fixtureList.get(i).getBody())).getBulletDamage());
                }
                //removes bullet
                bulletsToRemove.add((IBullet) (fixtureList.get(i).getBody().getUserData()));
            }
        }
    }

    private static boolean isCorrectFixtureType(Fixture fix, String str){
        return (str.equals(fix.getUserData()));// && fix.getBody().getUserData().getClass().equals(c));
    }

    //Handles the enemy collision with suitable action
    private void handlesEnemyCollision(Fixture fixA, Fixture fixB){
        if("ground".equals(fixB.getBody().getUserData())){
            enemyToChangeDirection.add((IEnemy) (fixA.getBody().getUserData()));
        }else if("body".equals(fixB.getUserData())){
            enemyToChangeDirection.add((IEnemy)(fixA.getBody().getUserData()));
            ((ICharacter)fixB.getBody().getUserData()).decHP(((IEnemy) (fixA.getBody().getUserData())).getDamageDeal());
        }
    }

    //Called when contact between two fixtures ends
    @Override
    public void endContact(Contact contact){
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if ("footSensor".equals(fa.getUserData())) {
            ((ICharacter) fa.getBody().getUserData()).leftGround();
        }
        if ("footSensor".equals(fb.getUserData())) {
            ((ICharacter) fb.getBody().getUserData()).leftGround();
        }
        if ("jumpPointSensor".equals(fa.getUserData())) {
            ((ICharacter) fb.getBody().getUserData()).toggleFollowerOnJumpPoint();
        }
        if ("jumpPointSensor".equals(fb.getUserData())) {
            ((ICharacter) fa.getBody().getUserData()).toggleFollowerOnJumpPoint();
        }
    }

    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }


    @Override
    public void beginContact(IContact contact) {
        beginContact((Contact) contact);
    }

    @Override
    public void endContact(IContact contact) {
        endContact((Contact) contact);
    }

    @Override
    public List<IPickupable> getItemsToRemove(){
        List<IPickupable> listToReturn = new ArrayList<IPickupable>(itemsToRemove.size());
        for (IPickupable pickup : itemsToRemove) {
            listToReturn.add(pickup);
        }
        itemsToRemove.clear();
        return listToReturn;
    }


    @Override
    public String getNewState() {
        String ret = null;
        if (newState != null) {
            ret = newState;
            newState = null;
        }
        return ret;
    }

    @Override
    public List<IEnemy> getEnemiesToChangeDirection() {
        List<IEnemy> listToReturn = new ArrayList<IEnemy>(enemyToChangeDirection.size());
        for (IEnemy enemy : enemyToChangeDirection) {
            listToReturn.add(enemy);
        }
        enemyToChangeDirection.clear();
        return listToReturn;
    }

    @Override
    public List<IBullet> getBulletsToRemove(){
        List<IBullet> listToReturn = new ArrayList<IBullet>(bulletsToRemove.size());
        for (IBullet clash : bulletsToRemove) {
            listToReturn.add(clash);
        }
        bulletsToRemove.clear();
        return listToReturn;
    }
}
