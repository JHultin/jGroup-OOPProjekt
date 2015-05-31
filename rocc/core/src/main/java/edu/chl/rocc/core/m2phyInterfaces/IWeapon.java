package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for weapons.
 *
 * @author Jenny Orell
 */
public interface IWeapon {

    /**
     * Create and return a bullet/projectile.
     */
    public IBullet createBullet(float x, float y, float xDir, float yDir);

    /**
     * @return the name/ID of the weapon.
     */
    public String getName();

    /**
     * @return the x-coordinate of where the bullets spawn.
     */
    public int getBulletSpawnX();

    /**
     * @return the y-coordinate of where the bullets spawn.
     */
    public int getBulletSpawnY();

    /**
     * Method that makes it easier for Java's garbage collector to delete objects.
     */
    public void dispose();
}
