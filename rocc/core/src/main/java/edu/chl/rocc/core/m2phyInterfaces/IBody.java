package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Interface for body objects.
 * Used because model classes cannot have dependencies to extern libraries.
 *
 * Created by Yen on 2015-05-11.
 */
public interface IBody {

    /**
     * Set linear velocity on center body with x-force and y-force
     * @param x x-coordinate of the center
     * @param y y-coordinate of the center
     */
    public void setLinearVelocity(float x, float y);

    /**
     * Applies force to center of the body with x-force and y-force
     * @param x x-coordinate of the center
     * @param y y-coordinate of the center
     */
    public void applyForceToCenter(float x, float y);

    /**
     * @return positions X-value of the body
     */
    public float getPositionX();

    /**
     * @return positions Y-value of the body
     */
    public float getPositionY();

    /**
     * Sets the userdata of the body.
     * @param userData userdata to be set.
     */
    public void setUserData(String userData);

    /**
     * Destroys body.
     */
    public void destroy();
}
