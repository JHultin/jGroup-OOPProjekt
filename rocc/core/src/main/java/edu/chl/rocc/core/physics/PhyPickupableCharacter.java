package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IPickupableCharacter;
import edu.chl.rocc.core.model.PickupableCharacter;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Created by Joel on 2015-05-12.
 */
public class PhyPickupableCharacter implements IPickupableCharacter {

    private final IPickupableCharacter pCharacter;
    private final Body body;
    private final World world;

    public PhyPickupableCharacter(String name, World world, int x, int y){
        this.pCharacter = new PickupableCharacter(name);
        this.world = world;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.STATIC;
        body = this.world.createBody(def);
        body.setUserData(this);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(32, 32);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_PICKUPABLE;
        fDef.filter.maskBits = BitMask.BIT_BODY;
        body.createFixture(fDef).setUserData("pickupCharacter");

    }

    @Override
    public float getX() {
        return body.getPosition().x;
    }

    @Override
    public float getY() {
        return body.getPosition().y;
    }

    @Override
    public String getName() {
        return pCharacter.getName();
    }
}
