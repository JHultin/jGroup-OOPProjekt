package edu.chl.rocc.core.m2phyInterfaces;

import org.jbox2d.dynamics.contacts.Contact;

/**
 * Created by Yen on 2015-05-11.
 */
public interface ICollisionListener {

    public void beginContact(Contact contact);

    public void endContact(Contact contact);

}
