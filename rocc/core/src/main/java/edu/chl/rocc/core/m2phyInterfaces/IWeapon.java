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
     * @return the name/ID of the weapon.
     */
    public String getName();

    /**
     * @return the x-coordinate of the weapon.
     */
    public float getX();

    /**
     * @return the y-coordinate of the weapon.
     */
    public float getY();

    public void dispose();

}
