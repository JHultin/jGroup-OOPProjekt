package edu.chl.rocc.core.model;

import edu.chl.rocc.core.physics.PhyCharacter;
import org.jbox2d.dynamics.World;

/**
 * A class handeling data and setters/getters for the main character.
 * Extends class Character.
 *
 * @author Jenny Orell
 */
public class MainCharacter extends PhyCharacter {

    private static final int MAX_HEALTH = 150;

    public MainCharacter(World world, int x, int y){
        super(world, x, y, "");
    }

    public int getMaxHP(){
        return MAX_HEALTH;
    }

}