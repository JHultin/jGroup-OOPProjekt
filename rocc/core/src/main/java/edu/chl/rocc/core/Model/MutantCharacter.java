package edu.chl.rocc.core.model;

import org.jbox2d.dynamics.World;

/**
 * A class handeling data and setters/getters for one type of follower character.
 * Extends class Character.
 *
 * @author Jenny Orell
 */
public class MutantCharacter extends Character {

    private static final int MAX_HEALTH = 100;

    public MutantCharacter(World world, int x, int y){
        super(world, x, y);
    }

    public int getMaxHP(){
        return MAX_HEALTH;
    }
}
