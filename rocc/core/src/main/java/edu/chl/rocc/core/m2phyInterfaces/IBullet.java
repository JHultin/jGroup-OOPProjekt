package edu.chl.rocc.core.m2phyInterfaces;

import org.jbox2d.dynamics.World;

/**
 * Interface for projectiles.
 *
 * @author Jenny Orell
 */
public interface IBullet {

    /**
    * Fires the projectile in a given direction.
    */
    public void fire();

    /**
    * Returns the x-coordinate of the projectile.
    */
    public float getX();

    /**
    * Returns the y-coordinate of the projectile.
    */
    public float getY();

    /**
    * Returns the name/ID of the projectile.
    */
    public String getName();

    public void dispose();
}
