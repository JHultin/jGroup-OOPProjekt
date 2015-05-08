package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IFood;

/**
 * Created by Joel on 2015-05-08.
 */
public class Food implements IFood {

    private final int x, y;

    public Food(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}
