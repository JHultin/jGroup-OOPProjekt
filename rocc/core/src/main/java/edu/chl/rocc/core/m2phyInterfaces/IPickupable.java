package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for all object in our game that the character can pick up
 *
 * Created by Joel on 2015-05-11.
 */
public interface IPickupable {

    public float getX();

    public float getY();

    public String getName();

    /**
     * Removes the object from the world
     */
    public void destroy();
}
