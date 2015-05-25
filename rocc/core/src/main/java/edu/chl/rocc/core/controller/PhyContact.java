package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.model.m2phyInterfaces.IFixture;
import edu.chl.rocc.core.model.physics.PhyFixture;
import edu.chl.rocc.core.model.factories.IContact;
import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Yen on 2015-05-20.
 */
public class PhyContact implements IContact {

    private Contact contact;

    public PhyContact(Contact contact){
        this.contact = contact;
    }

    @Override
    public IFixture getFixtureA() {
        return new PhyFixture(contact.getFixtureA());
    }

    @Override
    public IFixture getFixtureB() {
        return new PhyFixture(contact.getFixtureB());
    }
}
