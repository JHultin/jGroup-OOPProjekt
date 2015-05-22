package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.controller.ICollisionListener;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.Direction;
import edu.chl.rocc.core.utility.IDeathEvent;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel{

    /**
     * Creates the world, all pickupable items and teh blocks making the level.
     * The map must contain a tiledLayer named "ground" and objectlayers named "food" and "charcters"
     * defining the level.
     */
    public void constructWorld();

    /**
     *
     * @param body
     */
    public void addBlock(IBody body);

    /**
    * Move the character in a given direction.
    */
    public void moveSideways(Direction dir);

    /**
    * Move the followers in a given direction.
    */
    public void moveFollowers(Direction dir);

    public boolean characterIsMoving(ICharacter character);

    /**
    * Make the character jump by changing its y-coordinate.
    */
    public void jump();

    public void jumpFollowerIfPossible();

    public void shoot(float x, float y);

    /**
    * @return x-coordinate of the lead character.
    */
    public float getCharacterXPos();

    /**
    * @return y-coordinate of the lead character.
    */
    public float getCharacterYPos();


    /**
     * Updates the physical world, eg. all collisions and movement.
     * Do not call any methods from any physics object while this is handled
     * @param dt time since last update
     */
    public void updateWorld(float dt);

    /**
     * Returns all pickupable items on the map, used for drawing them
     * @return a list of all pickupable items
     */
    public List<IPickupable> getPickupables();

    /**
     *
     */
    public void addPickupable(IPickupable pickup);

    /**
     * Removes all items in given list from the world and the level.
     * Do not call while the world updates
     * @param itemsToRemove list of items that wil be removed
     */
    public void removeItems(List<IPickupable> itemsToRemove);

    /**
     * Add a weapon.
     */
    public void addWeapon(String name);

    /**
     * @return the current wielded weapon.
     */
    public IWeapon getWeapon();

    /**
    * @return list of all current existing projectiles in the level.
    */
    public List<IBullet> getBullets();

    /**
    * @return list of all the playable characters.
    */
    public List<ICharacter> getCharacters();

    public List<IEnemy> getEnemies();

    public void addEnemy(IEnemy enemy);

    /**
     * Sets a collitionlistener to the world, this will then handle all collistions
     * @param collisionListener listener to set to the world
     */
    public void setCollisionListener(ICollisionListener collisionListener);

    /**
     * Creates a new character, adds it to the player and the world
     * @param name description or id for the character
     */
    public void addCharacter(String name);

    public void addCharacter(String name, IDeathListener listener);

    /**
     * Randomly sets which character is in the front
     */
    public void changeLead();

    /**
     * Called to lower memory leak
     */
    public void dispose();

    public int getScore();

    public void incScore(int inc);

    public int getTime();

    public void handleDeath(IDeathEvent deathEvent);

    public void changeDirectionOnEnemies(List<IEnemy> enemyDirToChange);

    public void removeBullets(List<IBullet> bulletsToRemove);

    public void addJumpPoint(IJumpPoint jumpPoint);

    public void addFinish(IFinishPoint finish);

    public void setActiveCharacter(int activeIndex);
}
