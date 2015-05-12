package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.model.Food;

/**
 * Created by Joel on 2015-05-08.
 */
public class PhyFood implements IFood {

    private final IFood food;

    public PhyFood(float x, float y) {
        food = new Food(x, y);
    }

    @Override
    public float getX() {
        return food.getX();
    }

    @Override
    public float getY() {
        return food.getY();
    }

    @Override
    public String getName() {
        return food.getName();
    }
}
