package edu.chl.rocc.core.physics;

import static edu.chl.rocc.core.GlobalConstants.PPM;
import edu.chl.rocc.core.controller.MyContactListener;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.model.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyCharacter implements ICharacter {

    private final World world;
    private ICharacter character;
    private float width, height;
    private Body body;
    private MyContactListener listener;
    private int leftV;
    private int rightV;

    public PhyCharacter(World world, int x, int y){
        this.world = world;
        this.width = 18 / PPM;
        this.height = 35 / PPM;
        this.listener = new MyContactListener();
        this.world.setContactListener(this.listener);


        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_BODY;
        fDef.filter.maskBits = BitMask.BIT_GROUND;
        body.createFixture(fDef).setUserData("playerBody");

        //create foot sensor
        shape.setAsBox(width, height/4, new Vec2(0, -30 / PPM) ,0);
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_BODY;
        fDef.filter.maskBits = BitMask.BIT_GROUND;
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData("footSensor");
    }

    @Override
    public int getHP() {
        return this.character.getHP();
    }

    @Override
    public void setHP(int value) {
        this.character.setHP(value);
    }

    @Override
    public void incHP(int value) {
        this.character.incHP(value);
    }

    @Override
    public void decHP(int value) {
        this.character.decHP(value);
    }

    @Override
    public void move(Direction dir) {

        if(dir.equals(Direction.LEFT)){
           body.setLinearVelocity(new Vec2(-200 / PPM, 0));
            leftV  = leftV + 1;
        } else if(dir.equals(Direction.RIGHT)){
            body.setLinearVelocity(new Vec2(200 / PPM, 0));
            rightV = rightV + 1;
        } else if(dir.equals(Direction.UP)){

        } else if(dir.equals(Direction.DOWN)){

        } else if (dir.equals(Direction.NONE)){
            if(leftV > 0){
                body.setLinearVelocity(new Vec2(0, 0));
                leftV = leftV - 1;
            }else if(rightV > 0){
                body.setLinearVelocity(new Vec2(0, 0));
                rightV = rightV - 1;
            }
        }
    }

    public void moveFollower(int i){
        body.applyForceToCenter(new Vec2(100 * i, 0));
    }

    @Override
    public void jump() {
        if(this.listener.isPlayerOnGround() > 0){
            body.applyForceToCenter(new Vec2(0, 250));
        }
    }

    @Override
    public float getX() {
        return (body.getPosition().x-width) * PPM;
    }

    @Override
    public float getY() {
        return (body.getPosition().y-height) * PPM ;
    }
}
