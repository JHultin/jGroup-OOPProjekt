package edu.chl.rocc.core.m2phyInterfaces;

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

    public int getBulletDamage();


}
