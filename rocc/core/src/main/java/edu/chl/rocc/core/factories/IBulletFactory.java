package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;

/**
 * Interface for a bullet factory.
 *
 * @author Jenny Orell
 */
public interface IBulletFactory {

    /**
     * Create a bullet/projectile.
     * @return an instance of IBullet.
     */
    public IBullet createBullet(String name, float x, float y, float xDir, float yDir);
}
