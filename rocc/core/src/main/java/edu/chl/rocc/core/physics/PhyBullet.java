package edu.chl.rocc.core.physics;

import com.badlogic.gdx.math.Vector2;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.Bullet;
import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import static edu.chl.rocc.core.GlobalConstants.PPM;

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
    private float velocity;
    private final int bulletDamage;

    //private final Vector2 direction;

    /*
        public PhyBullet(World world, float x, float y, float xDir, float yDir, String name){
            this(World world, float x, float y, float xDir, float yDir, String name, false);
        }
     */


    //public PhyBullet(World world, float x, float y, float xDir, float yDir, String name /*, boolean isEnemyBullet*/){

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
        /*if(isEnemyBullet){
            fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_BODY;
        }else{
             fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_ENEMY;
        }*/
        fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_ENEMY | BitMask.BIT_FRAME;
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

    /*
        public boolean getIsEnemyBullet(){

        }
     */

    /*
    public Vector2 getDirection(){
        return this.direction;
    }

    public void setVelocity(Vec2 vec){
        this.velocity = vec;
    }


    public Vec2 getVelocity(){
        return new Vec2(500 / PPM, 0);
        */

    @Override
    public void destroy() {
        this.world.destroyBody(body);
    }
}
