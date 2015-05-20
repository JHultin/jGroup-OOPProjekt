package edu.chl.rocc.core.m2phyInterfaces;

import org.jbox2d.dynamics.World;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface ILevel {

    public void addBlock(IBody body);

    public void updateWorld(float dt);

    public World getWorld();

    public void addPickupable(IPickupable pickup);

    public void removePickupable(IPickupable pickup);

    public List<IPickupable> getPickupables();

    public void addJumpPoint(IJumpPoint jumpPoint);

    /**
    * Create a bullet/projectile.
    */
    public void createBullet(float x, float y, float xDir, float yDir);

    /**
    * Returns a list of all current existing projectiles in the level.
    */
    public List<IBullet> getBullets();

    /**
    * Adds a bullet/projectile to the list of existing projectiles.
    */
    public void addBullet(IBullet bullet);

    /**
    * Removes a bullet/projectile to the list of existing projectiles.
    */
    public void removeBullet(IBullet bullet);

    public List<IEnemy> getEnemies();

    public void addEnemy(IEnemy enemy);

    public void removeEnemy(IEnemy enemy);

    public void setAim(float x, float y);

    public void addFinish(IFinishPoint finishPoint);

    public void dispose();

    public int getTime();

    public void updateTime();

}
