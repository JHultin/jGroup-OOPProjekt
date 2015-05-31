package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for all object in our game that the character can pick up.
 *
 * Created by Joel on 2015-05-11.
 */
public interface IPickupable {

    /**
     * @return the x-coordinate of the object.
     */
    public float getX();

    /**
     * @return the y-coordinate of the object.
     */
    public float getY();

    /**
     * @return the name/ID of the object.
     */
    public String getName();

    /**
     * Removes the object from the world.
     */
    public void destroy();
}
