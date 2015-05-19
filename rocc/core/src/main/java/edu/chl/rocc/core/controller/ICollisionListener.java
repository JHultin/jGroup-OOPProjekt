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

    /**
     * Called when two fixtures collides that are able to collide
     * @param contact
     */
    public void beginContact(Contact contact);

    /**
     * Called when two fixtures are not in collision anymore that had collided
     * @param contact
     */
    public void endContact(Contact contact);

    /**
     * @return a list of Pickupable items to remove
     */
    public List<IPickupable> getItemsToRemove();

    /**
     * @return a list of enemies to change direction on
     */
    public List<IEnemy> getEnemiesToChangeDirection();

    /**
     * @return a list of bullets to remove
     */
    public List<IBullet> getBulletsToRemove();

    /**
     * @return the new state
     */
    public String getNewState();
}
