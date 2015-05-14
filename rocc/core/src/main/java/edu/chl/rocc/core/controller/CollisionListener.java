package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.model.Player;
import edu.chl.rocc.core.physics.PhyCharacter;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
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

    public CollisionListener(){
        itemsToRemove = new ArrayList<IPickupable>();
        enemyToChangeDirection = new ArrayList<IEnemy>();
    }

    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //Handles if on ground or not
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
            this.newState = "menu";
        }
        if ("finish".equals(fb.getUserData())) {
            this.newState = "menu";
        }
        if ("enemyUpperSensor".equals(fa.getUserData())){
            if("ground".equals(fb.getBody().getUserData())){
                enemyToChangeDirection.add((IEnemy) (fa.getBody().getUserData()));
            }else if("body".equals(fb.getUserData())){
                System.out.println("Take HP from character");
            }
        }
        if ("enemyUpperSensor".equals(fb.getUserData())){
            if("ground".equals(fa.getBody().getUserData())) {
                enemyToChangeDirection.add((IEnemy) (fb.getBody().getUserData()));
            }else if("body".equals(fa.getUserData())){
                System.out.println("Take HP from character");
            }
        }
    }

        //Called when contact between two fixtures ends
        @Override
        public void endContact(Contact contact){
            //update world måste vara klar innan detta görs

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
        public List<IPickupable> getItemsToRemove(){
            List<IPickupable> listToReturn = new ArrayList<IPickupable>(itemsToRemove.size());
            for (IPickupable pickup : itemsToRemove) {
                listToReturn.add(pickup);
            }
            itemsToRemove.clear();
            return listToReturn;
        }


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
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
