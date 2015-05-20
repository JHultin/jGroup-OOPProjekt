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

    // kan kanske ta bort world sen då body ej ska finnas och world finns i bulletfactory
    public PhyWeapon(World world, IBulletFactory bFac, String name, float x, float y){
        this.weapon = new Weapon(bFac, name);
    }

    @Override
    public void createBullet(float xDir, float yDir){
        this.weapon.createBullet(xDir, yDir);
    }

    @Override
    public String getName(){
        return this.weapon.getName();
    }

    @Override
    public float getX(){
        return this.weapon.getX();
    }

    @Override
    public float getY(){
        return this.weapon.getY();
    }

    @Override
    public void dispose() {

    }
}
