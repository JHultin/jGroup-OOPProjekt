package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;

/**
 * Class for weapons.
 * <br>Implements IWeapon.
 *
 * @author Jenny Orell
 */
public class Weapon implements IWeapon {

    private final IBulletFactory bulletFactory;

    private final String name;

    // bullet list??

    public Weapon(IBulletFactory bFac, String name){
        this.bulletFactory = bFac;
        this.name = name;
    }

    // Bortkommenterat från merge conflict fix i rebase
    /*
    @Override
    public void createBullet(float x, float y, float xDir, float yDir){
        this.bulletFactory.createBullet("", x, y, xDir, yDir);
        System.out.println("createWeapon in Bullet");
    }
    */

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void createBullet(float xDir, float yDir){
        this.bulletFactory.createBullet("", 0, 0, xDir, yDir);
    }

    @Override
    public float getX(){
        return 0;
    }

    @Override
    public float getY(){
        return 0;
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
