package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Class for projectiles.
 *
 * @author Jenny Orell
 */
public class Bullet implements IBullet {

    private final float x, y;
    private final String name;

    public Bullet(float x, float y, String name){
        this.x = x / PPM;
        this.y = y / PPM;
        this.name = name;
    }

    @Override
    public void fire(){ }

    @Override
    public float getX(){
        return this.x * PPM + 16;
    }

    @Override
    public float getY(){
        return this.y * PPM + 16;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void dispose() {

    }

    @Override
    public int getBulletDamage() {
        return 0;
    }
}
