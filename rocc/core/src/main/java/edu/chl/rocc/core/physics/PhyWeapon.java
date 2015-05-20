package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;

/**
 * Class for weapons handling the physics.
 * <br>Implements IWeapon.
 *
 * @author Jenny Orell
 */
public class PhyWeapon implements IWeapon {

    private final IBulletFactory bulletFactory;
    private final String name;

    public PhyWeapon(IBulletFactory bFac, String name){
        this.bulletFactory = bFac;
        this.name = name;
    }

    @Override
    public void createBullet(float x, float y, float xDir, float yDir){
        //this.bulletFactory.createBullet("", x, y, xDir, yDir);
        System.out.println("createWeapon in PhyBullet");
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void dispose(){

    }
}
