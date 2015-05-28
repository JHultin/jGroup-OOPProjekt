package edu.chl.rocc.core.logic.physics;

import edu.chl.rocc.core.logic.m2phyInterfaces.IBody;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;

/**
 * Created by Yen on 2015-05-11.
 */
public class PhyBody implements IBody{

    private final Body body;

    public PhyBody(Body body){
        this.body = body;
    }

    @Override
    public void setLinearVelocity(float x, float y) {
        body.setLinearVelocity(new Vec2(x, y));
    }

    @Override
    public void applyForceToCenter(float x, float y) {
        body.applyForceToCenter(new Vec2(x, y));
    }

    @Override
    public float getPositionX() {
       return body.getPosition().x;
    }

    @Override
    public float getPositionY() {
        return body.getPosition().y;
    }

    @Override
    public void setUserData(String userData) {
        body.setUserData(userData);
    }

    @Override
    public void destroy() {
        body.getWorld().destroyBody(body);
    }
}
