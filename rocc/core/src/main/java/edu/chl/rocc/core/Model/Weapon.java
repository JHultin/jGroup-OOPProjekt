package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;

import java.util.ArrayList;
import java.util.List;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Class for weapons.
 * <br>Implements IWeapon.
 *
 * @author Jenny Orell
 */
public class Weapon implements IWeapon {

    private final String name;
    private final float width, height;
    private float x, y;

    private List<IBullet> bullets;
    private final IBulletFactory bulletFactory;
    private final float bulletSpawnX, bulletSpawnY;

    public Weapon(IBulletFactory bFac, String name){
        this.bulletFactory = bFac;
        this.name = name;
        this.bullets = new ArrayList<IBullet>();

        // Hämta från textfil baserat på "name"
        this.bulletSpawnX = 32 / PPM;
        this.bulletSpawnY = 16 / PPM;
        this.width = 32 / PPM;
        this.height = 32 / PPM;
    }

    @Override
    public void createBullet(float xDir, float yDir){
        //this.bulletFactory.createBullet("", 0, 0, xDir, yDir);

        this.bulletFactory.createBullet("", this.getX() + this.bulletSpawnX,
                this.getY() + this.bulletSpawnY, xDir, yDir);
        System.out.println("Create bullet in factory.");
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public float getX(){
        return this.x;
    }

    @Override
    public float getY(){
        return this.y;
    }

    @Override
    public void dispose() {
        /*
        for (IBullet bullet : bullets){
            bullet.dispose();
        }
         */
    }
}
