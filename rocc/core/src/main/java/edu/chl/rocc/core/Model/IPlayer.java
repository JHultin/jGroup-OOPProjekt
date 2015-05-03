package edu.chl.rocc.core.model;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IPlayer {

    public void jump();

    public void move(Direction dir);

    public float getCharacterXPos();

    public float getCharacterYPos();
}
