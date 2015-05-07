package edu.chl.rocc.core.controller;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Yen on 2015-05-02.
 */
public class MyContactListener implements ContactListener {

    private int playerOnGround;

    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //Kolla om kroppen har en fotsensor, om JA så se vilken kropp den tillhör


        //Checks if the are in collision
        if(fa.getUserData() != null && fa.getUserData().equals("footSensor")){
            playerOnGround ++;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("footSensor")){
            playerOnGround ++;
        }
    }

    //called when contact between two fixtures ends
    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //checks if not in contact anymore
        if(fa.getUserData() != null && fa.getUserData().equals("footSensor")){
            playerOnGround --;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("footSensor")){
            playerOnGround --;
        }

    }

    public int isPlayerOnGround(){
        return playerOnGround;
    }


    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
