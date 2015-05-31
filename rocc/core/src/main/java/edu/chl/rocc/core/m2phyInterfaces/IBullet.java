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

    /**
     * Method that makes it easier for Java's garbage collector to delete objects.
     */
    public void dispose();

    /**
     * @return the amount of damage that the bullet does.
     */
    public int getBulletDamage();

    /**
     * Destroys the bullet and deletes it from the world.
     */
    public void destroy();
}
