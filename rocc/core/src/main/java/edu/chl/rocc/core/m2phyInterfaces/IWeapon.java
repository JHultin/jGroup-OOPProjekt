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
    public void createBullet(float x, float y, float xDir, float yDir);

    public String getName();

    public void dispose();

}
