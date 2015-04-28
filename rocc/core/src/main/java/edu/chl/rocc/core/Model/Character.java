package edu.chl.rocc.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * A class for the playable characters.
 * Handeling data and setters/getters.
 *
 * @author Jenny Orell
 */
public class Character {

    private final World world;
    private int maxHealth = 100;
    private int healthPoints;
    private Body body;
    private int width, height;

    public Character(World world){
        this.setHP(maxHealth);
        this.world = world;
        this.width = 18;
        this.height = 35;

        //Defining & creating body
        BodyDef def = new BodyDef();
        def.position.set(160,120);
        def.type = BodyType.DYNAMIC;
        body = this.world.createBody(def);

        //Defining & creating fixture
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width,height);
        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        body.createFixture(fDef);
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
            //xPos += 15;
            body.applyForceToCenter(new Vec2(200, 0));
        } else if(dir.equals(Direction.UP)){
            //yPos += 15;
            body.applyForceToCenter(new Vec2(200, 0));
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
