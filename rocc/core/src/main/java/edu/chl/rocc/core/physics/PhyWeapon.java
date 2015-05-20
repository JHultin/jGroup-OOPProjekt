package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.IBulletFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;
import java.util.List;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Class for weapons handling the physics.
 * <br>Implements IWeapon.
 *
 * @author Jenny Orell
 */
public class PhyWeapon implements IWeapon {

    private final World world;
    private final Body body;

    private final String name;
    private final float width, height;

    private final float bulletSpawnX, bulletSpawnY;
    private List<IBullet> bullets;
    private final IBulletFactory bulletFactory;

    // kan kanske ta bort world sen då body ej ska finnas och world finns i bulletfactory
    public PhyWeapon(World world, IBulletFactory bFac, String name, float x, float y){
        this.world = world;
        this.bulletFactory = bFac;
        this.name = name;
        this.bullets = new ArrayList<IBullet>();

        // Hämta från textfil baserat på "name"
        this.bulletSpawnX = 32 / PPM;
        this.bulletSpawnY = 16 / PPM;
        this.width = 32 / PPM;
        this.height = 32 / PPM;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
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
    public void createBullet(float xDir, float yDir){
        //IBullet bullet = new PhyBullet(this.getWorld(), x, y, xDir, yDir, "");
        //this.addBullet(bullet);

        this.bulletFactory.createBullet("", this.getX() + this.bulletSpawnX,
                                        this.getY() + this.bulletSpawnY, xDir, yDir);
        System.out.println("Create bullet in factory.");
    }

    @Override
    public float getX(){
        return (body.getPosition().x-width) * PPM;
    }

    @Override
    public float getY(){
        return (body.getPosition().y-height) * PPM;
    }

    @Override
    public String getName(){
        return this.name;
    }

    @Override
    public void dispose() {
        world.destroyBody(body);
    }
}
