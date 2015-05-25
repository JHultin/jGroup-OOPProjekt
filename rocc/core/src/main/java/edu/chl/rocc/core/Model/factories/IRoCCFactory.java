package edu.chl.rocc.core.model.factories;

import edu.chl.rocc.core.model.m2phyInterfaces.*;

/**
 * Iterface for factories creating all objects handling the logic in our game
 *
 * Created by Joel on 2015-05-08.
 */
public interface IRoCCFactory {

    public IPlayer createPlayer(String name);

    public ILevel createLevel (String name);

    public ICharacter createCharacter(String name, float x, float y);

    public IFood createFood(String name, int x, int y);

    public IWeapon createWeapon(String name, float x, float y);

    public IPickupableCharacter createPickupAbleCharacter(String name, int x, int y);

    public IEnemy createEnemy(String name, int x, int y, int hp);

}
