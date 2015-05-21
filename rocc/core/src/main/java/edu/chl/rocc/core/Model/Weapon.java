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
    //private float x, y;

    private List<IBullet> bullets;
    private final IBulletFactory bulletFactory;
    private final float bulletSpawnX, bulletSpawnY;

    public Weapon(IBulletFactory bFac, String name){
        this.bulletFactory = bFac;
        this.name = name;
        this.bullets = new ArrayList<IBullet>();

        // Hämta från textfil baserat på "name"
        this.bulletSpawnX = 45;
        this.bulletSpawnY = 22;
        this.width = 32 / PPM;
        this.height = 32 / PPM;
    }

    @Override
    public IBullet createBullet(float x, float y, float xDir, float yDir){
        return this.bulletFactory.createBullet("", x + this.bulletSpawnX,
                y + this.bulletSpawnY, xDir, yDir);
    }

    @Override
    public String getName(){
        return this.name;
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
