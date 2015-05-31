package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.observers.IGameLossable;
import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IDeathEvent;

import java.util.List;

/**
 * Interface for all main model classes.
 *
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel extends IGameLossable{

    /**
     * Creates the world, all pickupable items and teh blocks making the level.
     * The map must contain a tiledLayer named "ground" and objectlayers named "food" and "charcters"
     * defining the level.
     */
    public void constructWorld();

    public void constructWorld(IDeathListener listener);

    /**
     *
     * @param body
     */
    public void addBlock(IBody body);

    /**
     * Move the character in a given direction.
     * @param dir direction in which the character will be moved.
     */
    public void moveSideways(Direction dir);

    /**
     * Move the followers in a given direction.
     * @param dir direction in which the followers will be moved.
     */
    public void moveFollowers(Direction dir);

    /**
     * @return true if the given character is moving, false otherwise.
     * @param character the character to be controlled
     */
    public boolean characterIsMoving(ICharacter character);

    /**
    * Make the character jump by changing its y-coordinate.
    */
    public void jump();

    /**
     * Make the active character shoot/fire a bullet.
     * @param x the x-coordinate the bullet will be fired towards
     * @param y the y-coordinate the bullet will be fired towards
     */
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
     * Add a pickupable.
     * @param pickup pickupable to be added
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
     * @param name name/ID of the weapon
     */
    public void addWeapon(String name);

    /**
     * @return the current wielded weapon.
     */
    public IWeapon getWeapon();

    /**
     * Change the currently used weapon.
     */
    public void changeWeapon();

    /**
     * @return the x-coordinate of where the bullets spawn.
     */
    public int getBulletSpawnX();

    /**
     * @return the y-coordinate of where the bullets spawn.
     */
    public int getBulletSpawnY();

    /**
    * @return list of all current existing projectiles in the level.
    */
    public List<IBullet> getBullets();

    /**
    * @return list of all the playable characters.
    */
    public List<ICharacter> getCharacters();

    /**
     * @return a list of all enemies in the level.
     */
    public List<IEnemy> getEnemies();

    /**
     * Add an enemy to the level.
     * @param enemy enemy to be added
     */
    public void addEnemy(IEnemy enemy);

    /**
     * Sets a collitionlistener to the world, this will then handle all collistions.
     * @param collisionListener listener to set to the world
     */
    public void setCollisionListener(ICollisionListener collisionListener);

    /**
     * Creates a new character, adds it to the player and the world.
     * @param name description or id for the character
     */
    public void addCharacter(String name);

    /**
     * Creates a new character, adds it to the player and the world.
     * @param name description or id for the character
     * @param listener deathlistener
     */
    public void addCharacter(String name, IDeathListener listener);

    public void setDeathListener(IDeathListener deathListener);

    /**
     * Randomly sets which character is in the front
     */
    public void changeLead();

    /**
     * Called to lower memory leak.
     */
    public void dispose();

    /**
     * @return the score of the player.
     */
    public int getScore();

    /**
     * Increase the player's score.
     * @param inc the amount to increase the score with
     */
    public void incScore(int inc);

    /**
     * @return for how long the game has been run.
     */
    public int getTime();

    public void handleDeath(IDeathEvent deathEvent);

    public void changeDirectionOnEnemies(List<IEnemy> enemyDirToChange);

    /**
     * Remove bullets.
     * @param bulletsToRemove list of bullets to remove.
     */
    public void removeBullets(List<IBullet> bulletsToRemove);

    /**
     * Add a jump point to the level.
     * @param jumpPoint jump point to be added
     */
    public void addJumpPoint(IJumpPoint jumpPoint);

    /**
     * Add a finish point to the level.
     * @param finish finish point to be added
     */
    public void addFinish(IFinishPoint finish);

    /**
     * Set a character to be the active character.
     * @param activeIndex index in the character list of the character to be set as active.
     */
    public void setActiveCharacter(int activeIndex);

    /**
     * @return the active character.
     */
    public ICharacter getActiveCharacter();

    /**
     * @return the latest dead character
     */
    public String getDeadCharacterName();
}
