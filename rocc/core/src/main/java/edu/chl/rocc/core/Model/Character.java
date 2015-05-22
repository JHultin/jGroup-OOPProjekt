package edu.chl.rocc.core.model;

import edu.chl.rocc.core.Direction;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.utility.DeathEvent;
import edu.chl.rocc.core.utility.IDeathEvent;

import java.util.ArrayList;
import java.util.List;


/**
 * A class for the playable characters, handeling data and setters/getters.
 * <br>Implements ICharacter.
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
    private boolean damageTaken;

    private int timeCount;


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
            death("No more hp");
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
        damageTaken = true;
    }

    @Override
    public void move(Direction dir){
        if(!direction.equals(Direction.NONE)) {
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
        boolean tmpDamageTaken = damageTaken;

        if(damageTaken) {
            if (timeCount < 60) {//is used to decide for how long damageState will continue
                timeCount++;
            } else {
                damageTaken = false;
                timeCount = 0;
            }
        }
        if (!this.getDirection().equals(Direction.NONE)) {
            return "" + inAir + getDirection().toString()+ tmpDamageTaken;
        } else {
            return "" + inAir + getDirection().toString() + getLastDirection().toString() + tmpDamageTaken;
        }
    }

}
