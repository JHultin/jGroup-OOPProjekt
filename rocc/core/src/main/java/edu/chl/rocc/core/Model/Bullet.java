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

    public Bullet(float x, float y){
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX(){
        return this.x * PPM - 16;
    }

    @Override
    public float getY(){
        return this.y * PPM - 16;
    }
}
