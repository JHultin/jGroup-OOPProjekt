package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IEnemy;
import static edu.chl.rocc.core.GlobalConstants.PPM;
import java.util.ArrayList;

/**
 * A class for handeling enemies(except for box2D stuff)
 * Handeling data and setters/getters.
 *
 * Created by Yen on 2015-05-09.
 */
public class Enemy implements IEnemy{

    private int healthPoints;
    private final float x, y;

    public Enemy(int healthPoints, String enemyName, float x, float y){  //maybe not enemyName
       this.setHP(healthPoints);
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
    public int getHP() {
        return this.healthPoints;
    }

    @Override
    public void setHP(int value) {
        this.healthPoints = value;
    }

    @Override
    public void decHP(int value) {
        this.healthPoints = this.healthPoints - value;
    }

    @Override
    public ArrayList movePattern() {
        return null;
    }
}
