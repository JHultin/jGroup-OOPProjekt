package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for weapons.
 *
 * @author Jenny Orell
 */
public interface IWeapon {

    /**
     * Create a bullet/projectile.
     */
    public void createBullet(float xDir, float yDir);

    /**
     * @return the x-coordinate of the weapon.
     */
    public float getX();

    /**
     * @return the y-coordinate of the weapon.
     */
    public float getY();

    public String getName();

    public void dispose();

}
