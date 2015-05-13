package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.model.Direction;

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
    * @return x-coordinate of the character.
    */
    public float getCharacterXPos(int i);

    /**
    * @return y-coordinate of the character.
    */
    public float getCharacterYPos(int i);

    /**
    * Adds a character to the character list.
    */
    public void addCharacter(String name);

    /**
     * @return list of all characters.
     */
    public List<ICharacter> getCharacters();

    public void dispose();

    public void setActiveCharacter(int i);

    /**
    * Change which character the player is playing as.
    */
    public void setActiveCharacter(ICharacter character);
}
