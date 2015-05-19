package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IEnemy;
import edu.chl.rocc.core.m2phyInterfaces.IPickupable;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

import java.util.List;

/**
 * Created by Yen on 2015-05-11.
 */
public interface ICollisionListener {

    public void beginContact(Contact contact);

    public void endContact(Contact contact);

    public List<IPickupable> getItemsToRemove();

    public List<IEnemy> getEnemiesToChangeDirection();

    public List<IBullet> getBulletsToRemove();

    public String getNewState();
}
