package edu.chl.rocc.core.logic.physics;

import static edu.chl.rocc.core.utility.GlobalConstants.PPM;

import edu.chl.rocc.core.observers.DeathEvent;
import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.logic.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.logic.model.Character;
import edu.chl.rocc.core.utility.CharacterLoader;
import edu.chl.rocc.core.observers.IDeathEvent;
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
    private int characterOnGround;
    private Direction direction;
    private Direction airDir;

    // Characteristics
    private final int speed;
    private final int numberOfJumps;
    private int currentJump = 0;
    private final int jumpForce;
    private final int airForce;
    private final int weapon;

    private boolean isOnJumpPoint;
    private boolean isMoving;

    private final Fixture fixture;

    public PhyCharacter(World world, float x, float y, String name){
        this.world = world;
        this.width = 18 / PPM;
        this.height = 35 / PPM;
        this.character = new Character(name);

        this.isOnJumpPoint = false;
        this.isMoving = false;

        CharacterLoader cl = new CharacterLoader(name);
        this.speed         = cl.getCharecaristic("Speed");
        this.numberOfJumps = cl.getCharecaristic("NumberOfJumps");
        this.jumpForce     = cl.getCharecaristic("JumpForce");
        this.airForce      = cl.getCharecaristic("AirForce");
        this.weapon        = cl.getCharecaristic("Weapon");

        airDir = Direction.NONE;

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
        fDef.filter.categoryBits = BitMask.BIT_FOLLOWER;
        fDef.filter.maskBits = BitMask.BIT_GROUND | BitMask.BIT_JUMPPOINT;
        fixture = body.createFixture(fDef);
        fixture.setUserData("body");

        //create foot sensor
        shape.setAsBox(width/2, height/4, new Vec2(0, -30 / PPM) ,0);
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
        if (character.getHP() <= value){
            this.death("No more HP");
        } else {
            this.character.decHP(value);
        }
    }

    @Override
    public void move(Direction dir) {
        if (dir != direction && (characterOnGround > 0)) {
            if (dir.equals(Direction.LEFT)) {
                body.setLinearVelocity(new Vec2(-speed / PPM, 0));
                this.isMoving = true;
            } else if (dir.equals(Direction.RIGHT)) {
                body.setLinearVelocity(new Vec2( speed / PPM, 0));
                this.isMoving = true;
            } else if (dir.equals(Direction.NONE)) {
                body.setLinearVelocity(new Vec2(0, 0));
                this.isMoving = false;
            }
            direction = dir;
        } else if (dir != airDir && characterOnGround == 0){
            if (dir.equals(Direction.LEFT)) {
                body.applyForceToCenter(new Vec2(-airForce, 0));
            } else if (dir.equals(Direction.RIGHT)) {
                body.applyForceToCenter(new Vec2( airForce, 0));
            } else if (dir.equals(Direction.NONE)) {
                if (airDir == Direction.LEFT) {
                    body.applyForceToCenter(new Vec2( airForce, 0));
                } else if (airDir == Direction.RIGHT) {
                    body.applyForceToCenter(new Vec2(-airForce, 0));
                }
            }
            airDir = dir;
        }
        this.character.move(dir);

        if(this.character.isFollower() && this.isOnJumpPoint && this.isMoving){
            this.jump();
        }
    }

    @Override
    public boolean isMoving(){
        return this.isMoving;
    }

    @Override
    public void jump() {
       if(currentJump < numberOfJumps) {
           this.body.setLinearVelocity(new Vec2(0, 0));
           this.body.applyForceToCenter(new Vec2(0, jumpForce));
           airDir = Direction.UP;

           character.leftGround();
           this.currentJump ++;
       }
    }

    //Write on one line
    @Override
    public void toggleFollowerOnJumpPoint(){
        this.isOnJumpPoint = !this.isOnJumpPoint;
    }

    @Override
    public void hitGround(){
        if (characterOnGround == 0){
            if (airDir.equals(Direction.LEFT)) {
                body.setLinearVelocity(new Vec2(-speed / PPM, 0));
            } else if (airDir.equals(Direction.RIGHT)) {
                body.setLinearVelocity(new Vec2( speed / PPM, 0));
            } else if (airDir.equals(Direction.NONE)) {
                body.setLinearVelocity(new Vec2(0, 0));
                this.isMoving = false;
            }
            direction = airDir;

            this.character.hitGround();

            this.currentJump = 0;
        }
        characterOnGround++;


    }

    @Override
    public void leftGround(){
        characterOnGround--;
        if (characterOnGround == 0){
            if(airDir != Direction.UP) {
                this.body.setLinearVelocity(new Vec2(0, 0));
                this.currentJump ++;
            }
            airDir = Direction.NONE;
            this.character.leftGround();
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
    public int getWeapon(){
        return this.weapon;
    }

    @Override
    public void dispose() {
        character.dispose();
        world.destroyBody(body);
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

    @Override
    public Direction getDirection(){
        return character.getDirection();
    }

    @Override
    public Direction getLastDirection(){
        return character.getLastDirection();
    }

    @Override
    public boolean inAir() {
        return character.inAir();
    }

    @Override
    public boolean isFollower(){
        return this.character.isFollower();
    }

    @Override
    public void setAsFollower(){
        fixture.getFilterData().categoryBits = BitMask.BIT_FOLLOWER;
        fixture.getFilterData().maskBits = BitMask.BIT_GROUND | BitMask.BIT_JUMPPOINT;
        this.character.setAsFollower();
    }

    @Override
    public void setAsLead(){
        fixture.getFilterData().categoryBits = BitMask.BIT_BODY;
        fixture.getFilterData().maskBits = BitMask.BIT_GROUND | BitMask.BIT_PICKUPABLE | BitMask.BIT_ENEMY | BitMask.BIT_JUMPPOINT
                | BitMask.BIT_FINISH;
        this.character.setAsLead();
    }

    @Override
    public void addDeathListener(IDeathListener listener) {
        this.character.addDeathListener(listener);
    }

    @Override
    public void removeDeathListener(IDeathListener listener) {
        this.character.removeDeathListener(listener);
    }

    @Override
    public void death(String message) {
        this.character.death(new DeathEvent(this, message));
    }

    @Override
    public void death(IDeathEvent deathEvent) {
        this.character.death(deathEvent);
    }

    @Override
    public String getMoveState(){
        return character.getMoveState();
    }
}

