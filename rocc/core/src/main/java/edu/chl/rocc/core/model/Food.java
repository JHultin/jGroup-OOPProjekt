package edu.chl.rocc.core.model;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;
import edu.chl.rocc.core.m2phyInterfaces.IFood;

/**
 * Class for food objects handling everything logical.
 * <br>Implements IFood.
 *
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
        return x * PPM - 16;
    }

    @Override
    public float getY() {
        return y * PPM - 8;
    }

    @Override
    public String getName() {
        return "food";
    }

    @Override
    public void destroy() {

    }
}
