package edu.chl.rocc.core.m2phyInterfaces;

/**
 * Created by Yen on 2015-05-11.
 */
public interface IBody {

    public void setLinearVelocity(float x, float y);

    public void applyForceToCenter(float x, float y);

    public float getPositionX();

    public float getPositionY();

    public void setUserData(String userData);

}
