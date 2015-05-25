package edu.chl.rocc.core.model.physics;

import edu.chl.rocc.core.model.factories.IBulletFactory;
import edu.chl.rocc.core.model.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.model.pure.Weapon;

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
    public void dispose() {

    }
}
