package edu.chl.rocc.core.m2phyInterfaces;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface ILevel {

    /**
     * Add a body to a list of bodies to dispose.
     */
    public void addBodyToDisposeList(IBody body);

    /**
     * Update the world and timer.
     */
    public void updateWorld(float dt);

    /**
     * Add a pickupable object to the level.
     */
    public void addPickupable(IPickupable pickup);

    /**
     * Remove a pickupable object from the level.
     */
    public void removePickupable(IPickupable pickup);

    /**
     * @return a list of all pickupable objects in the level.
     */
    public List<IPickupable> getPickupables();

    /**
     * Add a jump point to the level.
     */
    public void addJumpPoint(IJumpPoint jumpPoint);

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

    /**
     * @return a list of all enemies in the level.
     */
    public List<IEnemy> getEnemies();

    /**
     * Add an enemy to the level.
     */
    public void addEnemy(IEnemy enemy);

    /**
     * Remove an enemy from the level.
     */
    public void removeEnemy(IEnemy enemy);

    /**
     * Add a finish point to the level.
     */
    public void addFinish(IFinishPoint finishPoint);

    /**
     * Method that makes it easier for Java's garbage collector to delete objects.
     */
    public void dispose();

    /**
     * @return for how long the game has run.
     */
    public int getTime();

    /**
     * Update the time.
     */
    public void updateTime(float dt);

}
