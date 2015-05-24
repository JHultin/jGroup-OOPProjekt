package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.*;
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

    public ArrayList<IPickupable> itemsToRemove;
    public String newState;

    public ArrayList<IEnemy> enemyToChangeDirection;
    public ArrayList<IBullet> bulletsToRemove;

    public CollisionListener(){
        itemsToRemove = new ArrayList<IPickupable>();
        enemyToChangeDirection = new ArrayList<IEnemy>();
        bulletsToRemove = new ArrayList<IBullet>();
    }

    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //Handles if character on ground
        if ("footSensor".equals(fa.getUserData())) {
            ((ICharacter) fa.getBody().getUserData()).hitGround();
        }
        if ("footSensor".equals(fb.getUserData())) {
            ((ICharacter) fb.getBody().getUserData()).hitGround();
        }

        if ("food".equals(fa.getUserData()) && fa.getBody().getUserData() instanceof IFood) {
            itemsToRemove.add((IFood) (fa.getBody().getUserData()));
        }
        if ("food".equals(fb.getUserData()) && fb.getBody().getUserData() instanceof IFood) {
            itemsToRemove.add((IFood) (fb.getBody().getUserData()));
        }
        if ("pickupCharacter".equals(fa.getUserData()) && fa.getBody().getUserData() instanceof IPickupableCharacter) {
            itemsToRemove.add((IPickupableCharacter) (fa.getBody().getUserData()));
        }
        if ("pickupCharacter".equals(fb.getUserData()) && fb.getBody().getUserData() instanceof IPickupableCharacter) {
            itemsToRemove.add((IPickupableCharacter) (fb.getBody().getUserData()));
        }
        if ("jumpPointSensor".equals(fa.getUserData())) {
            ((ICharacter) fb.getBody().getUserData()).toggleFollowerOnJumpPoint();
        }
        if ("jumpPointSensor".equals(fb.getUserData())) {
            ((ICharacter) fa.getBody().getUserData()).toggleFollowerOnJumpPoint();
        }
        if ("finish".equals(fa.getUserData())) {
            this.newState = "victory";
        }
        if ("finish".equals(fb.getUserData())) {
            this.newState = "victory";
        }

        /**
         * When enemy walks into a body or a wall into change direction
         */
        if ("enemyUpperSensor".equals(fa.getUserData())){
            if("ground".equals(fb.getBody().getUserData())){
                enemyToChangeDirection.add((IEnemy) (fa.getBody().getUserData()));
            }else if("body".equals(fb.getUserData())){
                enemyToChangeDirection.add((IEnemy)(fa.getBody().getUserData()));
                ((ICharacter)fb.getBody().getUserData()).decHP(((IEnemy) (fa.getBody().getUserData())).getDamageDeal());
            }
        }
        if ("enemyUpperSensor".equals(fb.getUserData())){
            if("ground".equals(fa.getBody().getUserData())) {
                enemyToChangeDirection.add((IEnemy) (fb.getBody().getUserData()));
            }else if("body".equals(fa.getUserData())){
                enemyToChangeDirection.add((IEnemy) (fb.getBody().getUserData()));
                ((ICharacter)fa.getBody()).decHP(((IEnemy) (fb.getBody().getUserData())).getDamageDeal());
            }
        }

        //When bullet hits enemy it takes damage
        if("bullet".equals(fa.getUserData())){
            if("enemyUpperSensor".equals(fb.getUserData())){
                ((IEnemy) fb.getBody().getUserData()).decHP(((IBullet) (fa.getBody())).getBulletDamage());
                System.out.println("HP " + ((IEnemy) fa.getBody().getUserData()).getHP());
            }
            if("ground".equals(fb.getUserData())){
                System.out.println(fa.getBody());
            }
            //removes bullet
            bulletsToRemove.add((IBullet) (fa.getBody().getUserData()));
        }
        if("bullet".equals(fb.getUserData())){
            if("enemyUpperSensor".equals(fa.getUserData())){
                ((IEnemy) (fa.getBody().getUserData())).decHP(((IBullet) (fb.getBody().getUserData())).getBulletDamage());
                System.out.println("HP " + ((IEnemy) fa.getBody().getUserData()).getHP());
            }
            if("ground".equals(fa.getUserData())){
                System.out.println(fa.getBody().getUserData());
            }
            //removes bullet
            bulletsToRemove.add((IBullet) (fb.getBody().getUserData()));
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
