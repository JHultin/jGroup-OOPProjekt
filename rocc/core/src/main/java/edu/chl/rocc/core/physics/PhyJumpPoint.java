package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IJumpPoint;
import edu.chl.rocc.core.utility.Direction;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;

/**
 * Class for jump points handling physics.
 * <br>Implements IJumpPoint.
 *
 * @author Jenny Orell
 */
public class PhyJumpPoint implements IJumpPoint {

    private final World world;
    private final Body body;
    private final Direction jumpDir;

    /**
     * Creates JumpPoint. Constructor.
     *
     * @param world
     * @param dir
     * @param x
     * @param y
     */
    public PhyJumpPoint(World world, Direction dir, float x, float y){
        this.world = world;
        this.jumpDir = dir;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x, y);
        def.type = BodyType.STATIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32 / PPM, 32 / PPM);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_JUMPPOINT;
        fDef.filter.maskBits = BitMask.BIT_BODY | BitMask.BIT_FOLLOWER;
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData("jumpPointSensor");
    }
}
