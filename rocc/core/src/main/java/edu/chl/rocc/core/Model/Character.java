package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import org.jbox2d.dynamics.World;

/**
 * A class for the playable characters.
 * Handeling data and setters/getters.
 *
 * @author Jenny Orell
 */
public class Character implements ICharacter {

    private int maxHealth = 100;
    private int healthPoints;

    public Character(){
        this.setHP(maxHealth);
    }

    public Character(World world, int x, int y){
        this.setHP(maxHealth);
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
    }

    public void moveFollower(int i){

    }

    /*
    * Make the character jump by changing its y-coordinate.
    */
    public void jump(){
    }

    /*
    * Returns the x-coordinate of the character.
    */
    public float getX(){
        return 0;
    }

    /*
    * Returns the y-coordinate of the character.
    */
    public float getY(){
        return 0;
    }

}
