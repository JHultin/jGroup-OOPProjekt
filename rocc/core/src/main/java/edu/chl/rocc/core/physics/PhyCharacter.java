package edu.chl.rocc.core.physics;

import static edu.chl.rocc.core.GlobalConstants.PPM;
import edu.chl.rocc.core.controller.MyContactListener;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.model.*;
import edu.chl.rocc.core.model.Character;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyCharacter implements ICharacter {

    private final World world;
    private final ICharacter character;
    private final float width, height;
    private final Body body;
    private MyContactListener listener;
    private int leftV;
    private int rightV;

    public PhyCharacter(World world, int x, int y, String name){
        this.world = world;
        this.width = 18 / PPM;
        this.height = 35 / PPM;
        this.listener = new MyContactListener();
        this.world.setContactListener(this.listener); //should be made only once so every character has the same listener
        this.character = new Character(name);

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(x / PPM, y / PPM);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);
        body.setUserData(this);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.filter.categoryBits = BitMask.BIT_BODY;
        fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_PICKUPABLE;
        body.createFixture(fDef).setUserData("body");

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

    @Override
    public void moveFollower(Direction dir){
        this.move(dir);
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

    @Override
    public String getName() {
        return character.getName();
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        } else if (o == null){
            return false;
        } else if (this.getClass() != o.getClass()){
            return false;
        } else {
            return this.hashCode() == o.hashCode();
        }
    }

    @Override
    public int hashCode(){
        int hash = 0;
        hash += world.hashCode();
        hash += character.hashCode();
        hash += width * 733  + height * 547;
        hash += body.hashCode();
        return hash;
    }
}
