package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IEnemy;
import edu.chl.rocc.core.model.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import java.util.*;
import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * A class to handle enemies (including box2D stuff)
 *
 * Created by Yen on 2015-05-09.
 */
public class PhyEnemy implements IEnemy {

    private final World world;
    private final IEnemy enemy;
    private final float width, height;
    private final Body body;
    private final BodyDef def;
    private final FixtureDef fDef;
    private int dir;

    public PhyEnemy(World world, float x, float y, int healthPoints){

        // Enemy may also have a weapon

        this.world = world;
        this.width = 16 / PPM;
        this.height = 25 / PPM;
        this.enemy = new Enemy(healthPoints, "", 0, 0);
        this.dir = 2;

        //Defining & creating body
        def = new BodyDef();
        def.position.set(x , y);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);
        body.setLinearVelocity(new Vec2(-1, 0));
        body.setUserData(this);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_ENEMY;
        fDef.filter.maskBits = BitMask.BIT_GROUND;
        body.createFixture(fDef).setUserData("enemyBody");

        //create upperbody sideSensor
        shape.setAsBox(width, height/4, new Vec2(0, 0) ,0);
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_ENEMY;
        fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_BODY;
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData("enemyUpperSensor");
    }

    @Override
    public void changeMoveDirection(){
        if(dir == 0 || dir == 2) {
            if(dir == 2) {
                body.setLinearVelocity(new Vec2(2, 0));
            }
            dir = dir - 2;
        }else if(dir == -2 || dir == 1){
            if (dir == -2){
                body.setLinearVelocity(new Vec2(-2,0));
                dir = dir + 3;
            }else if(dir == 1){
                dir++;
            }
        }
    }

    @Override
    public float getX() {
        return (body.getPosition().x - width) * PPM;
    }

    @Override
    public float getY() {
        return (body.getPosition().y - height) * PPM;
    }

    @Override
    public int getHP() {
        return this.enemy.getHP();
    }

    @Override
    public void setHP(int value) {
        this.enemy.setHP(value);
    }

    @Override
    public void decHP(int value) {
        this.enemy.decHP(value);
    }

    @Override
    public void dispose() {
        this.enemy.dispose();
        world.destroyBody(body);
    }
}
