package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IEnemy;
import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * A class for handeling enemies(except for box2D stuff)
 * Handeling data and setters/getters.
 *
 * Created by Yen on 2015-05-09.
 */
public class Enemy implements IEnemy{

    private int healthPoints;
    private final float x, y;
    private final String name;
    private Direction direction;
    private Direction lastDir;
    private boolean damageTaken;//A boolean to keep track if damageBeingTaken

    private int timeCount;

    public Enemy(int healthPoints, String enemyName, float x, float y){
        this.setHP(healthPoints);
        this.x = x;
        this.y = y;
        this.name = enemyName;
        this.direction = Direction.LEFT;
        this.lastDir = this.direction;
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
        if(value < 0){
            //death("No more hp");
            System.out.print("Input value for health points cannot be negative.");
            // die-method??
        }

        if(value <= healthPoints && value >= 0){
            this.healthPoints = value;
        }else{
            this.healthPoints = value;
        }
    }

    @Override
    public void decHP(int value) {
        this.setHP(this.healthPoints - value);
        damageTaken = true;
    }

    @Override
    public void dispose() {

    }

    @Override
    public void changeMoveDirection() {

    }

    @Override
    public int getDamageDeal() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void move(Direction dir) {
            if(!direction.equals(Direction.NONE)) {
                lastDir = direction;
            }
            direction = dir;
    }

    @Override
    public String getMoveState(){
        boolean tmpDamageTaken = damageTaken;

        if(damageTaken) {
            if (timeCount < 60) {//is used to decide for how long damageState will continue
                timeCount++;
            } else {
                damageTaken = false;
                timeCount = 0;
            }
        }

        return "" + false + getDirection().toString() + tmpDamageTaken;
    }
}
