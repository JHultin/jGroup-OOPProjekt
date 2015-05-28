package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.model.Weapon;

/**
 * Class for weapons handling the physics.
 * <br>Implements IWeapon.
 *
 * @author Jenny Orell
 */
public class PhyWeapon implements IWeapon {

    private final IWeapon weapon;
    //private final float width, height;

    public PhyWeapon(IBulletFactory bFac, String name){
        this.weapon = new Weapon(bFac, name);
    }

    @Override
    public IBullet createBullet(float x, float y, float xDir, float yDir){
        return this.weapon.createBullet(x, y, xDir, yDir);
    }

    @Override
    public String getName(){
        return this.weapon.getName();
    }

    @Override
    public int getBulletSpawnX(){
        return this.weapon.getBulletSpawnX();
    }

    @Override
    public int getBulletSpawnY(){
        return this.weapon.getBulletSpawnY();
    }

    @Override
    public void dispose() {

    }
}
