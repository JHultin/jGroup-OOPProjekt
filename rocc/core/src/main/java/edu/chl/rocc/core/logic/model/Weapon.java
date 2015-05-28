package edu.chl.rocc.core.logic.model;

import edu.chl.rocc.core.logic.factories.IBulletFactory;
import edu.chl.rocc.core.logic.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.logic.m2phyInterfaces.IWeapon;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;

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
    private final IBulletFactory bulletFactory;
    private final int bulletSpawnX, bulletSpawnY;

    public Weapon(IBulletFactory bFac, String name){
        this.bulletFactory = bFac;
        this.name = name;

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
    public int getBulletSpawnX(){
        return this.bulletSpawnX;
    }

    @Override
    public int getBulletSpawnY(){
        return this.bulletSpawnY;
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
