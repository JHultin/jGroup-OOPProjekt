package edu.chl.rocc.core;

/**
 * Created by Jenny on 2015-04-22.
 */
public class Character {

    private int maxHealth = 100;
    private int healthPoints;

    public Character(){
        this.setHP(maxHealth);
    }

    public int getHP(){
        return this.healthPoints;
    }

    public void setHP(int value){
        if(value <= maxHealth){
            this.healthPoints = value;
        } else{
            this.healthPoints = maxHealth;
        }
    }

    public void incHP(int value){
        this.setHP(this.getHP() + value);
    }

    public void decHP(int value){
        this.setHP(this.getHP() - value);
    }

}
