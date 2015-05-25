package edu.chl.rocc.core.model.pure;

import edu.chl.rocc.core.model.m2phyInterfaces.IBullet;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Class for projectiles.
 * <br>Implements IBullet.
 *
 * @author Jenny Orell
 */
public class Bullet implements IBullet {

    private final float x, y;
    private final String name;

    public Bullet(String name, float x, float y){
        this.x = x / PPM;
        this.y = y / PPM;
        this.name = name;
    }

    @Override
    public float getX(){
        return this.x * PPM + 2;
    }

    @Override
    public float getY(){
        return this.y * PPM + 2;
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

    @Override
    public void destroy() {

    }
}
