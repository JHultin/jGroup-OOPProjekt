package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.Direction;

/**
 * Created by Yen on 2015-05-09.
 */
public interface IEnemy {

    /**
     * @return the X position of enemy
     */
    public float getX();

    /**
     * @return the Y position of enemy
     */
    public float getY();

    /**
     * @return the HP of enemy
     */
    public int getHP();

    /**
     * Sets the HP of enemy to value
     * @param value
     */
    public void setHP(int value);

    /**
     * Increases the HP of enemy with value
     * @param value
     */
    public void decHP(int value);

    /**
     * Disposes the enemy
     */
    public void dispose();

    /**
     * Changes the current direction of the enemy
     */
    public void changeMoveDirection();

    /**
     * @return the damage the enemy deals
     */
    public int getDamageDeal();

    /**
     * @return the name of the enemy
     */
    public String getName();

    /**
    * @return the direction of the enemy
    */
    public Direction getDirection();

    /**
     * Created for handle the direction for the visual enemy
     * @param dir
     */
    public void move(Direction dir);

    /**
     * @return the enemy moveState as a string
     */
    public String getMoveState();
}
