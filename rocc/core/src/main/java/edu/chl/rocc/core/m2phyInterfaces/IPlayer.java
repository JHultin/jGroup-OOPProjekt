package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.Direction;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IPlayer {

    public void jump();

    public void jumpFollower();

    /**
    * Move the front character in a given direction.
    */
    public void move(Direction dir);

    /**
    * Move the follower characters towards the front character.
    */
    public void moveFollowers(Direction dir);

    /**
    * @return x-coordinate of the lead character.
    */
    public float getCharacterXPos();

    /**
    * @return y-coordinate of the lead character.
    */
    public float getCharacterYPos();

    /**
    * Adds a character to the character list.
    */
    public void addCharacter(String name);

    public void addCharacter(String name, IDeathListener listener);

    /**
     * @return list of all characters.
     */
    public List<ICharacter> getCharacters();

    /**
     * Add/create a weapon to the weapon list.
     */
    public void addWeapon(String name);

    /**
     * @return the weapon at the given index in the weapon list.
     */
    public IWeapon getWeapon();

    /**
     * @return a list of all the players weapons.
     */
    public List<IWeapon> getWeapons();

    /**
     * Adds a bullet to the bullet list.
     */
    public void addBullet(IBullet bullet);

    /**
     * @return a list of all existing bullets in the world.
     */
    public List<IBullet> getBullets();

    public void shoot(float xDir, float yDir);

    public void dispose();

    /**
     * Adds a value to the total score
     * @param value
     */
    public void addToScore(int value);

    /**
     * @return score
     */
    public int getScore();

    /**
     * Change which character the player is playing as.
     */
    public void setActiveCharacter(int i);

    /**
     * Change which character the player is playing as.
     */
    public void setFrontCharacter(ICharacter character);

    /**
     * @return the index of the front character in the character list.
     */
    public int getFrontCharacterIndex();

    /**
     * @return the distance between the front character and a follower.
     */
    public float getDistance(int i);
}
