package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.physics.PhyBullet;
import org.jbox2d.dynamics.World;

/**
 * Class for a PhyBullet factory.
 * <br>Implements IBulletFactory.
 *
 * @author Jenny Orell
 */
public class PhyBulletFactory implements IBulletFactory {

    private final World world;
    private final int width, height;

    public PhyBulletFactory(World world){
        this.world = world;

        this.width = 2;
        this.height = 2;
    }

    @Override
    public IBullet createBullet(String name, float x, float y, float xDir, float yDir){
        return new PhyBullet(this.world, name, x - width, y - height, xDir, yDir);
    }
}
