package edu.chl.rocc.core.model;

import org.jbox2d.dynamics.World;

/**
 * A class handeling data and setters/getters for the main character.
 * Extends class Character.
 *
 * @author Jenny Orell
 */
public class MainCharacter extends Character{

    private static final int MAX_HEALTH = 150;

    public MainCharacter(World world){
        super(world);
    }

    public int getMaxHP(){
        return MAX_HEALTH;
    }

}