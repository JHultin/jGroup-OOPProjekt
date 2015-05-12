package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.*;

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

    public IPickupableCharacter createPickupAbleCharacter(String name, int x, int y);

}
