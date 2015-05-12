package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.ICollisionListener;
import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.model.Player;
import edu.chl.rocc.core.physics.PhyCharacter;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.contacts.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yen on 2015-05-02.
 */
public class CollisionListener implements ContactListener, ICollisionListener {

    public ArrayList<Body> bodiesToRemove;

    public CollisionListener(){
        bodiesToRemove = new ArrayList<Body>();
    }

    //called when contact between two fixtures begins
    @Override
    public void beginContact(Contact contact) {
        //update world måste vara klar innan detta görs

        //Fetches the two fixtures
        Fixture fa = contact.getFixtureA();
        Fixture fb = contact.getFixtureB();

        //Kolla om kroppen har en fotsensor, om JA så se vilken kropp den tillhör
        if ("footSensor".equals(fa.getUserData())) {
            ((ICharacter)fa.getBody().getUserData()).hitGround();
        }
        if ("footSensor".equals(fb.getUserData())) {
            ((ICharacter)fb.getBody().getUserData()).hitGround();
        }
        if (fa.getUserData() != null && fa.getUserData() instanceof IFood) {
            ((ILevel) (fa.getBody().getUserData())).removeFood((IFood) fa.getUserData());
            bodiesToRemove.add(fa.getBody());
        }
        if (fb.getUserData() != null && fb.getUserData() instanceof IFood) {
            ((ILevel) (fb.getBody().getUserData())).removeFood((IFood) fb.getUserData());
            bodiesToRemove.add(fb.getBody());
        }
        if ("pickupCharacter".equals(fa.getUserData())) {

        }
        if ("pickupCharacter".equals(fb.getUserData())) {
            
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
    }

    @Override
    public List<Body> getBodiesToRemove() {
        List<Body> listToReturn = new ArrayList<Body>(bodiesToRemove.size());
        for (Body body : bodiesToRemove){
            listToReturn.add(body);
        }
        bodiesToRemove.clear();
        return listToReturn;
    }


    @Override
    public void preSolve(Contact contact, Manifold manifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse contactImpulse) {

    }
}
