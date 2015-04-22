package edu.chl.rocc.core.Model;

/**
 * A class for the playable characters.
 * Handeling data and setters/getters.
 *
 * @author Jenny Orell
 */
public class Character {

    private int maxHealth = 100;
    private int healthPoints;

    public Character(){
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
    * Increase character's health with a chosen value.
    */
    public void incHP(int value){
        this.setHP(this.getHP() + value);
    }

    /*
    * Decrease character's health with a chosen value.
    */
    public void decHP(int value){
        this.setHP(this.getHP() - value);
    }

}
