package edu.chl.rocc.core.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.utility.DeathEvent;
import edu.chl.rocc.core.utility.IDeathEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * A class for the playable characters.
 * Handeling data and setters/getters.
 *
 * @author Jenny Orell
 */
public class Character implements ICharacter {

    private final int maxHealth = 100;
    private int healthPoints;
    private final String name;

    private Direction direction;
    private Direction lastDir;
    private boolean inAir;

    private boolean isFollower;

    private final List<IDeathListener> deathListeners;


    public Character(String name){
        this.setHP(maxHealth);
        this.name = name;

        this.direction = Direction.RIGHT;
        this.lastDir = this.direction;

        isFollower = true;

        this.deathListeners = new ArrayList<IDeathListener>();
    }

    @Override
    public int getHP(){
        return this.healthPoints;
    }

    @Override
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

    @Override
    public void incHP(int value){
        this.setHP(this.getHP() + value);
    }

    @Override
    public void decHP(int value){
        this.setHP(this.getHP() - value);
    }

    @Override
    public void move(Direction dir){}

    @Override
    public void moveFollower(Direction dir){
        if(!dir.equals(Direction.NONE)){
            lastDir = direction;
        }
        direction = dir;
    }

    @Override
    public boolean isMoving(){
        return false;
    }

    @Override
    public void jump(){}

    @Override
    public void jumpIfFollower(){ }

    @Override
    public void toggleFollowerOnJumpPoint(){ }

    @Override
    public boolean isOnJumpPoint(){
        return false;
    }

    @Override
    public void hitGround() {
        inAir = false;
    }

    @Override
    public void leftGround() {
        inAir = true;
    }

    @Override
    public float getX(){
        return 0;
    }

    @Override
    public float getY(){
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void dispose() {

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
        hash += maxHealth * 257;
        hash += name.hashCode();
        return hash;
    }

    public void setCurrentDirection(Direction dir){
        if(!dir.equals(Direction.NONE)){
            lastDir = direction;
        }
        direction = dir;

    }


    @Override
    public Direction getDirection(){
        return direction;
    }

    @Override
    public Direction getLastDirection(){
        return lastDir;
    }


    @Override
    public boolean inAir(){
        return inAir;
    }


    @Override
    public boolean isFollower(){
        return this.isFollower;
    }

    @Override
    public void setAsFollower(){
        this.isFollower = true;
    }

    @Override
    public void setAsLead(){
        this.isFollower = false;
    }

    @Override
    public void addDeathListener(IDeathListener listener) {
        this.deathListeners.add(listener);
    }

    @Override
    public void removeDeathListener(IDeathListener listener) {
        this.deathListeners.remove(listener);
    }

    @Override
    public void death(String message) {
        IDeathEvent deathEvent = new DeathEvent(this, message);
        death(deathEvent);
    }

    @Override
    public void death(IDeathEvent deathEvent) {
        for(IDeathListener deathListener : deathListeners){
            deathListener.deathTriggered(deathEvent);
        }
    }

    @Override
    public String getMoveState(){
/*
        if (inAir() == true) {
            if (getDirection().equals(Direction.LEFT)) {
                this.moveState = "jumpLeft";
            } else if (getDirection().equals(Direction.RIGHT)) {
                this.moveState = "jumpRight";
            } else {
                if (getLastDirection().equals(Direction.LEFT)) {
                    this.moveState = "jumpLeft";
                } else {
                    this.moveState = "jumpRight";
                }
            }
        } else if (getDirection().equals(Direction.RIGHT)) {
            this.moveState = "moveRight";
        } else if (getDirection().equals(Direction.LEFT)) {
            this.moveState = "moveLeft";
        } else if (getDirection().equals(Direction.NONE)) {
            if (getLastDirection().equals(Direction.LEFT)) {
                this.moveState = "idleLeft";
            } else {
                this.moveState = "idleRight";
            }
        }
*/
        if(getDirection() == Direction.NONE) {
            System.out.println("" + inAir + getDirection().toString()+ getLastDirection().toString());
            System.out.println(getName());
            return "" + inAir + getDirection().toString() + getLastDirection().toString();
        }else {
            System.out.println("" + inAir() + getDirection().toString());
            System.out.println(getName());
            return "" + inAir + getDirection().toString();
        }
    }

}
