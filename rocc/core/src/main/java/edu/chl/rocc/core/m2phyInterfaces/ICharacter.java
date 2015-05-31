package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IMortal;

/**
 * An interface describing a character.
 * <br>Extends IMortal.
 *
 * Created by Joel on 2015-05-03.
 */
public interface ICharacter extends IMortal {

    /**
     * @return the character's health.
     */
    public int getHP();

    /**
     * Set character's health with a chosen value.
     * @param value value to set the health as.
     */
    public void setHP(int value);

    /**
     * Increase character's health with a given value.
     * @param value value to increase the health with.
     */
    public void incHP(int value);

    /**
     * Decrease character's health with a given value.
     * @param value value to decrease the health with.
     */
    public void decHP(int value);

    /**
     * Move the character in a given direction.
     * @param dir direction to move the character towards.
     */
    public void move(Direction dir);

    /**
     * @return true if the character is moving, false otherwise.
     */
    public boolean isMoving();

    /**
    * Make the character jump by changing its y-coordinate.
    */
    public void jump();

    /**
     * Teleport the character to a given location.
     * @param x x-coordinate of the location
     * @param y y-coordinate of the location
     */
    public void teleport(float x, float y);

    /**
     * Toggle the value if the character/follower is on a jump point.
     */
    public void toggleFollowerOnJumpPoint();

    /**
     * Tell the character that it is on the ground.
     */
    public void hitGround();

    /**
     * Tell the character that it is airborne.
     */
    public void leftGround();

    /**
     * @return x-coordinate of the character.
     */
    public float getX();

    /**
    * @return y-coordinate of the character.
    */
    public float getY();

    /**
     * @return the name/ID of the character.
     */
    public String getName();

    /**
     * @return the weapon ID of the character.
     */
    public int getWeapon();

    /**
     * Method that makes it easier for Java's garbage collector to delete objects.
     */
    public void dispose();

    /**
     * @return the direction of the character.
     */
    public Direction getDirection();

    /**
     * Used to render idle images.
     * @return the character's last direction that is not NONE.
     */
    public Direction getLastDirection();

    /**
     * @return true if the character is in air, false otherwise.
     */
    public boolean inAir();

    /**
     * @return true if the character is a follower, false otherwise.
     */
    public boolean isFollower();

    /**
     * Set the character as a follower.
     */
    public void setAsFollower();

    /**
     * Set the character as active/front character.
     */
    public void setAsLead();

    /**
     * @return current Character state.
     */
    public String getMoveState();

}
