package edu.chl.rocc.core.model.physics;

import edu.chl.rocc.core.model.m2phyInterfaces.IFinishPoint;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

/**
 * Created by Joel on 2015-05-15.
 */
public class PhyFinishPoint implements IFinishPoint {

    private final World world;
    private final Body body;

    public PhyFinishPoint (World world, float x, float y, float width, float height ){
        this.world = world;

        BodyDef bDef = new BodyDef();
        bDef.position.set(x, y);
        bDef.type = BodyType.STATIC;
        this.body = world.createBody(bDef);
        this.body.setUserData(this);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width, height);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_FINISH;
        fDef.filter.maskBits = BitMask.BIT_BODY;
        fDef.isSensor = true;

        body.createFixture(fDef).setUserData("finish");

    }

    @Override
    public void dispose() {

    }
}
