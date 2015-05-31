package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.Bullet;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;

/**
 * Class for projectiles handling the physics.
 * <br>Implements IBullet.
 *
 * @author Jenny Orell
 */
public class PhyBullet implements IBullet {

    private final World world;
    private final Body body;

    private final IBullet bullet;
    private final float velocity;
    private final int bulletDamage;

    public PhyBullet(World world, String name, float x, float y, float xDir, float yDir /*, boolean isEnemyBullet*/){
        this.world = world;

        //this.direction = vec;
        bulletDamage = 5;

        this.bullet = new Bullet(name, x / PPM, y / PPM);
        this.velocity = 500 / PPM;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.KINEMATIC;
        body = this.world.createBody(def);
        body.setUserData(this);

        //Defining & creating fixture
        CircleShape shape = new CircleShape();
        shape.setRadius(3 / PPM);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_BULLET;
        fDef.filter.maskBits = BitMask.BIT_ENEMY | BitMask.BIT_FRAME;
        body.createFixture(fDef).setUserData("bullet");
        body.setLinearVelocity(new Vec2(xDir * velocity, yDir * velocity));
    }

    @Override
    public float getX(){
        return body.getPosition().x * PPM + 2;
    }

    @Override
    public float getY(){
        return body.getPosition().y * PPM + 2;
    }

    @Override
    public String getName(){
        return this.bullet.getName();
    }

    @Override
    public void dispose() {
        this.bullet.dispose();
        this.world.destroyBody(body);
    }

    @Override
    public int getBulletDamage() {
        return bulletDamage;
    }

    @Override
    public void destroy() {
        this.world.destroyBody(body);
    }
}
