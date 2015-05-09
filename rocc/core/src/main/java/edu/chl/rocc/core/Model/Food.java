package edu.chl.rocc.core.model;

import static edu.chl.rocc.core.GlobalConstants.PPM;
import edu.chl.rocc.core.m2phyInterfaces.IFood;

/**
 * Created by Joel on 2015-05-08.
 */
public class Food implements IFood {

    private final float x, y;

    public Food(float x, float y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public float getX() {
        return x * PPM;
    }

    @Override
    public float getY() {
        return y * PPM;
    }
}
