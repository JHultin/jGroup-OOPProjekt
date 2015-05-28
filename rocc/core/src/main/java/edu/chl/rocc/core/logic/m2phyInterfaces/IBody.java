package edu.chl.rocc.core.logic.m2phyInterfaces;

/**
 * Created by Yen on 2015-05-11.
 */
public interface IBody {

    /**
     * Set linear velocity on body with x-force and y-force
     * @param x
     * @param y
     */
    public void setLinearVelocity(float x, float y);

    /**
     * Applies force to center of the body with x-force and y-force
     * @param x
     * @param y
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
     * Sets the userdata of the body
     * @param userData
     */
    public void setUserData(String userData);

    /**
     * Destroys body
     */
    public void destroy();
}
