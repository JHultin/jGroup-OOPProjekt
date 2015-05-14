package edu.chl.rocc.core.m2phyInterfaces;

import java.util.ArrayList;

/**
 * Created by Yen on 2015-05-09.
 */
public interface IEnemy {

    public float getX();

    public float getY();

    public int getHP();

    public void setHP(int value);

    public void decHP(int value);

    public void dispose();

    public void changeMoveDirection();
}
