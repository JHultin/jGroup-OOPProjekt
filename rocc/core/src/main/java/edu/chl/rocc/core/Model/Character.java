package edu.chl.rocc.core.model;

import com.badlogic.gdx.math.Vector2;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import org.jbox2d.common.Vec2;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.*;

/**
 * A class for the playable characters.
 * Handeling data and setters/getters.
 *
 * @author Jenny Orell
 */
public class Character implements ICharacter {

    private final World world;
    private int maxHealth = 100;
    private int healthPoints;
    private Body body;
    private int width, height;
    private int BIT_Body;
    private int BIT_Ground;

    public Character(World world){
        this.setHP(maxHealth);
        this.world = world;
        this.width = 18;
        this.height = 35;
        this.BIT_Body = 4;
        this.BIT_Ground = 2;
        this.world.setContactListener(new MyContactListener()); //Ska flyttas

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(160,120);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        FixtureDef fDef = new Fixture();
        fDef.shape = shape;
        fDef.filter.categoryBits = BIT_Body;
        fDef.filter.maskBits = BIT_Ground;
        body.createFixture(fDef).setUserData("playerBody");

        //create foot sensor
        shape.setAsBox(width,height/4,new Vec2(0,-30),0);
        fDef.shape = shape;
        fDef.filter.categoryBits = BIT_Body;
        fDef.filter.maskBits = BIT_Ground;
        fDef.isSensor = true;
        body.createFixture(fDef).setUserData("footSensor");
    }

    /*
    * Returns the character's health.
    */
    public int getHP(){
        return this.healthPoints;
    }

    /*
    * Set character's health with a chosen value.
    */
    public void setHP(int value){
        if(value < 0){
            System.out.print("Input value for health points cannot be negative.");
            // die-method??
        }

        if(value <= maxHealth && value >= 0){
            this.healthPoints = value;
        }else{
            this.healthPoints = maxHealth;
        }
    }

    /*
    * Increase character's health with a given value.
    */
    public void incHP(int value){
        this.setHP(this.getHP() + value);
    }

    /*
    * Decrease character's health with a given value.
    */
    public void decHP(int value){
        this.setHP(this.getHP() - value);
    }

    /*
    * Move the character in a given direction.
    */
    public void move(Direction dir){
        if(dir.equals(Direction.LEFT)){
            body.applyForceToCenter(new Vec2(-1000, 0));
        } else if(dir.equals(Direction.RIGHT)){
            body.applyForceToCenter(new Vec2(1000, 0));
        } else if(dir.equals(Direction.UP)){

        } else if(dir.equals(Direction.DOWN)){

        } else if (dir.equals(Direction.NONE)){

        }

    }

    public void jump(){
        body.applyForceToCenter(new Vec2(0, 1000));
    }

    /*
    * Returns the x-coordinate of the character.
    */
    public float getX(){
        return body.getPosition().x-width;
    }

    /*
    * Returns the y-coordinate of the character.
    */
    public float getY(){
        return body.getPosition().y-height;
    }

}
