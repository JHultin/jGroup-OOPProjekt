package edu.chl.rocc.core.m2phyInterfaces;

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
