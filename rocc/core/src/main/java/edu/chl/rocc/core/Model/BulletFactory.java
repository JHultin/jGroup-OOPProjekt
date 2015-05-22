package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.Bullet;

/**
 * Class for a Bullet factory.
 * <br>Implements IBulletFactory.
 *
 * @author Jenny Orell
 */
public class BulletFactory implements IBulletFactory {

    public BulletFactory(){

    }

    @Override
    public IBullet createBullet(String name, float x, float y, float xDir, float yDir){
        return new Bullet(name, x, y);
    }
}
