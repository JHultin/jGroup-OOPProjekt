package edu.chl.rocc.core.model.m2phyInterfaces;

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

    public void dispose();

}
