package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.model.Direction;

/**
 * Created by Joel on 2015-05-03.
 */
public interface ICharacter {

    public int getHP();

    public void setHP(int value);

    public void incHP(int value);

    public void decHP(int value);

    public void move(Direction dir);

    public void moveFollower(Direction dir);

    public void jump();

    public float getX();

    public float getY();
}
