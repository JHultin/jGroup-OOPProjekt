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

    private boolean playerOnGround;

    @Override
    public void beginContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("footSensor")){
            playerOnGround = true;
            System.out.println("Player on ground");
        }
        if(fb.getUserData() != null && fb.getUserData().equals("footSensor")){
            playerOnGround = true;
            System.out.println("Player on ground");
        }
    }


    @Override
    public void endContact(Contact contact) {

        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        if(fa.getUserData() != null && fa.getUserData().equals("footSensor")){
            playerOnGround = false;
            System.out.println("Player not on ground");
        }
        if(fb.getUserData() != null && fb.getUserData().equals("footSensor")){
            playerOnGround = false;
            System.out.println("Player not on ground");
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
