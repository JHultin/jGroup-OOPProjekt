package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.model.Weapon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;
import java.util.List;

import static edu.chl.rocc.core.GlobalConstants.PPM;

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
