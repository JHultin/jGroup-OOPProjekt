package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
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

    private int playerOneOnGround;
    private int playerTwoOnGround;
    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //Kolla om kroppen har en fotsensor, om JA så se vilken kropp den tillhör
        /*if(fa.isSensor()){
            if(fa.getBody().getUserData().equals("firstCharacter")){
                playerOneOnGround++;
            }else if(fa.getBody().getUserData().equals("secondCharacter")){
                playerTwoOnGround++;
            }
        }
        if(fb.isSensor()){
            if(fb.getBody().getUserData().equals("firstCharacter")){
                playerOneOnGround++;
            }else if(fb.getBody().getUserData().equals("secondCharacter")){
                playerTwoOnGround++;
            }
        }
        */





        //Checks if the are in collision
        if(fa.getUserData() != null && fa.getUserData().equals("footSensor")){
            playerOnGround ++;
        }
        if(fb.getUserData() != null && fb.getUserData().equals("footSensor")){
            playerOnGround ++;
        }

        if(fa.getUserData() != null && fa.getUserData() instanceof IFood){
            ((ILevel)(fa.getBody().getUserData())).removeFood((IFood)fa.getUserData());
        }
        if(fb.getUserData() != null && fb.getUserData() instanceof IFood){
            ((ILevel)(fb.getBody().getUserData())).removeFood((IFood)fb.getUserData());
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
