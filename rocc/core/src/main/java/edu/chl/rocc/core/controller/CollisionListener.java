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
 *
 * Handles collision between two fixtures
 */
public class CollisionListener implements ContactListener, ICollisionListener {

    private final List<IPickupable> itemsToRemove;
    private String newState;

    private final List<IEnemy> enemyToChangeDirection;
    private final List<IBullet> bulletsToRemove;

    public CollisionListener(){
        itemsToRemove = new ArrayList<IPickupable>();
        enemyToChangeDirection = new ArrayList<IEnemy>();
        bulletsToRemove = new ArrayList<IBullet>();
    }



    /**
     * Handles when two fixture comes in contact
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {
        List<Fixture> fixtureList = new ArrayList<Fixture>();

        //Fetches the two fixtures and adds them to the fixtureList
        fixtureList.add(contact.getFixtureA());
        fixtureList.add(contact.getFixtureB());


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
            //If sidecharacter is in contact with a jumpPointSensor, then jump
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
        fixtureList.clear();
    }

    // Checks if Fixture is equal to str. Return boolean
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

    /**
     * Handles when two fixture ends contact
     * @param contact
     */
    @Override
    public void endContact(Contact contact){
        List<Fixture> fixtureList = new ArrayList<Fixture>();
        fixtureList.add(contact.getFixtureA());
        fixtureList.add(contact.getFixtureB());

        for(int i=0; i<2; i++) {
            if ("footSensor".equals(fixtureList.get(i).getUserData())) {
                ((ICharacter) fixtureList.get(i).getBody().getUserData()).leftGround();
            }
            if ("jumpPointSensor".equals(fixtureList.get(i).getUserData())) {
                ((ICharacter) fixtureList.get(1-i).getBody().getUserData()).toggleFollowerOnJumpPoint();
            }
        }
        fixtureList.clear();
    }

    /**
     * Ain't in use
     *
     * @param contact
     * @param manifold
     */
    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    /**
     * Ain't in use
     *
     * @param contact
     * @param contactImpulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }

    /**
     * Calls upon beginContact in this. From ICollisionListener
     *
     * @param contact
     */
    @Override
    public void beginContact(IContact contact) {
        beginContact((Contact) contact);
    }

    /**
     * Calls upon endContact in this. From ICollisionListener
     *
     * @param contact
     */
    @Override
    public void endContact(IContact contact) {
        endContact((Contact) contact);
    }

    /**
     * Returns the items that should be removed in a list
     *
     * @return listToReturn
     */
    @Override
    public List<IPickupable> getItemsToRemove(){
        List<IPickupable> listToReturn = new ArrayList<IPickupable>(itemsToRemove.size());
        for (IPickupable pickup : itemsToRemove) {
            listToReturn.add(pickup);
        }
        itemsToRemove.clear();
        return listToReturn;
    }

    /**
     * Gets the newState
     *
     * @return ret
     */
    @Override
    public String getNewState() {
        String ret = null;
        if (newState != null) {
            ret = newState;
            newState = null;
        }
        return ret;
    }

    /**
     * Returns the list of enemies that should change direction
     *
     * @return listToReturn
     */
    @Override
    public List<IEnemy> getEnemiesToChangeDirection() {
        List<IEnemy> listToReturn = new ArrayList<IEnemy>(enemyToChangeDirection.size());
        for (IEnemy enemy : enemyToChangeDirection) {
            listToReturn.add(enemy);
        }
        enemyToChangeDirection.clear();
        return listToReturn;
    }

    /**
     * Returns the list of bullets that should be removed
     *
     * @return listToReturn
     */
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
