package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.*;

/**
 * Interface for factories creating all objects handling the logic in our game
 *
 * Created by Joel on 2015-05-08.
 */
public interface IRoCCFactory {

    /**
     * Creates a player with a given ID/name.
     * @return the created player.
     */
    public IPlayer createPlayer(String name);

    /**
     * Creates a level with a given ID/name.
     * @return the created level.
     */
    public ILevel createLevel (String name);

    /**
     * Creates a character with a given ID/name at a specific location.
     * @return the created character.
     */
    public ICharacter createCharacter(String name, float x, float y);

    /**
     * Creates a food object with a given ID/name at a specific location.
     * @return the created food.
     */
    public IFood createFood(String name, int x, int y);

    /**
     * Creates a weapon with a given ID/name at a specific location.
     * @return the created weapon.
     */
    public IWeapon createWeapon(String name, float x, float y);

    /**
     * Creates a character that the player can pick up, with a given ID/name at a specific location.
     * @return the created pickupable character.
     */
    public IPickupableCharacter createPickupAbleCharacter(String name, int x, int y);

    /**
     * Creates an enemy with a given ID/name at a specific location.
     * @return the created enemy.
     */
    public IEnemy createEnemy(String name, int x, int y, int hp);

}
