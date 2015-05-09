package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.Bullet;
import org.jbox2d.collision.shapes.CircleShape;
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
    private final float width, height;
    private final Body body;

    public PhyBullet(World world, int x, int y, String name){
        this.world = world;
        this.width = 5 / PPM;
        this.height = 5 / PPM;
        this.bullet = new Bullet(name);

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        CircleShape shape = new CircleShape();
        shape.setRadius(5);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_PICKUPABLE;
        fDef.filter.maskBits = BitMask.BIT_GROUND;
        body.createFixture(fDef).setUserData("body");

    }
}
