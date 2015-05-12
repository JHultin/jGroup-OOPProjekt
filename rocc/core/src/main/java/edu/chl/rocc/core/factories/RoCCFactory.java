package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.model.*;
import edu.chl.rocc.core.model.Character;

/**
 * Created by Joel on 2015-05-08.
 */
public class RoCCFactory implements IRoCCFactory {

    public RoCCFactory(){

    }

    @Override
    public IPlayer createPlayer(String name) {
        return new Player(this);
    }

    @Override
    public ILevel createLevel(String name) {
        return new Level();
    }

    @Override
    public ICharacter createCharacter(String name, float x, float y) {
        return new Character(name);
    }

    @Override
    public IFood createFood(String name, int x, int y) {
        return new Food(x, y);
    }

    @Override
    public IPickupableCharacter createPickupAbleCharacter(String name, int x, int y) {
        return new PickupableCharacter(name);
    }
}
