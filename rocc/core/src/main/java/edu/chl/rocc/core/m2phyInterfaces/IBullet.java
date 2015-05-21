package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for projectiles.
 *
 * @author Jenny Orell
 */
public interface IBullet {

    /**
     * @return the x-coordinate of the projectile.
     */
    public float getX();

    /**
     * @return the y-coordinate of the projectile.
     */
    public float getY();

    /**
     * @return the name/ID of the projectile.
     */
    public String getName();

    public void dispose();

    public int getBulletDamage();

    /**
     * Destroys the bullet and deletes it from the world.
     */
    public void destroy();
}
