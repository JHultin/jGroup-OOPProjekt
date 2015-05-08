package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.model.Food;

/**
 * Created by Joel on 2015-05-08.
 */
public class PhyFood implements IFood {

    private final IFood food;

    public PhyFood(int x, int y) {
        food = new Food(x, y);
    }

    @Override
    public int getX() {
        return food.getX();
    }

    @Override
    public int getY() {
        return food.getY();
    }
}
