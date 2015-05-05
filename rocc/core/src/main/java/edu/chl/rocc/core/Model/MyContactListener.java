package edu.chl.rocc.core.model;

import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Yen on 2015-05-02.
 */
public class MyContactListener implements ContactListener {

    private boolean playerOnGround;

    @Override
    public void beginContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("playerBody")){  //Should check with footsensor instead
            playerOnGround = true;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("playerBody")){
            playerOnGround = true;
        }
    }


    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("playerBody")){
            playerOnGround = false;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("playerBody")){
            playerOnGround = false;
        }

    }

    public boolean isPlayerOnGround(){
        return playerOnGround;
    }


    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
