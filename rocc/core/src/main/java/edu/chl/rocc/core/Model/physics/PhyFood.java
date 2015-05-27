package edu.chl.rocc.core.model.physics;

import edu.chl.rocc.core.model.m2phyInterfaces.IFood;
import edu.chl.rocc.core.model.pure.Food;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;

/**
 * Created by Joel on 2015-05-08.
 */
public class PhyFood implements IFood {

    private final IFood food;
    private final World world;
    private final Body body;

    public PhyFood(World world, float x, float y) {
        food = new Food(x, y);
        this.world = world;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x, y);
        def.type = BodyType.STATIC;
        body = this.world.createBody(def);
        body.setUserData(this);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(16 / PPM, 8 / PPM);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_PICKUPABLE;
        fDef.filter.maskBits = BitMask.BIT_BODY;
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData("food");
    }

    @Override
    public float getX() {
        return food.getX();
    }

    @Override
    public float getY() {
        return food.getY();
    }

    @Override
    public String getName() {
        return food.getName();
    }

    @Override
    public void destroy() {
        this.world.destroyBody(body);
        System.out.println(body);
    }
}
