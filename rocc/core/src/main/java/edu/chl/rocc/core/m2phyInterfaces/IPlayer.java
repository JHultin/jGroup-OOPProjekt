package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.model.Direction;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IPlayer {

    public void jump();

    public void move(Direction dir);

    public float getCharacterXPos(int i);

    public float getCharacterYPos(int i);

    public void addCharacter(ICharacter c);
}
