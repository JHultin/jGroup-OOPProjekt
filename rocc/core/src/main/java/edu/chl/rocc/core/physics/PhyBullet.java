package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.Bullet;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Class for projectiles handling the physics.
 *
 * @author Jenny Orell
 */
public class PhyBullet implements IBullet {

    private final World world;
    private final IBullet bullet;

    private final Body body;
    private final Vec2 direction;

    public PhyBullet(World world, float x, float y, Vec2 vec, String name){
        this.world = world;
        this.direction = vec;
        this.bullet = new Bullet(x / PPM, y / PPM, name);

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.KINEMATIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_BULLET;
        fDef.filter.maskBits = BitMask.BIT_GROUND;
        body.createFixture(fDef).setUserData("bullet");

        //Fire the bullet.
        //this.fire(direction);
        //this.fire(vec);
    }

    @Override
    public void fire(){
        body.setLinearVelocity(this.getDirection());
    }

    @Override
    public float getX(){
        return body.getPosition().x * PPM + 16;
    }

    @Override
    public float getY(){
        return body.getPosition().y * PPM + 16;
    }

    @Override
    public String getName(){
        return this.bullet.getName();
    }

    public Vec2 getDirection(){
        return this.direction;
    }
}
