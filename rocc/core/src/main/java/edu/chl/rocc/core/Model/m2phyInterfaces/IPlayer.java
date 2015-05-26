package edu.chl.rocc.core.model.m2phyInterfaces;

import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.utility.Direction;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IPlayer {

    public void jump();

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

    public void removeLead();

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

    public IBullet shoot(float xDir, float yDir);

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

    public void incScore(int inc);

    /**
     * Change which character the player is playing as.
     */
    public void setActiveCharacter(int i);

    public void cycleActivePlayer();

    /**
     * @return the index of the front character in the character list.
     */
    public int getFrontCharacterIndex();
}
