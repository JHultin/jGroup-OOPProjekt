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

    public PhyBulletFactory(World world){
        this.world = world;
    }

    @Override
    public IBullet createBullet(String name, float x, float y, float xDir, float yDir){
        System.out.println("Create bullet. x: " + x + " , y: " + y);
        return new PhyBullet(this.world, name, x, y, xDir, yDir);
    }
}
