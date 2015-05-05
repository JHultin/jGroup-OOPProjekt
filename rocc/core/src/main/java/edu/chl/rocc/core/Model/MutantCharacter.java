package edu.chl.rocc.core.model;

import edu.chl.rocc.core.physics.PhyCharacter;
import org.jbox2d.dynamics.World;

/**
 * A class handeling data and setters/getters for one type of follower character.
 * Extends class Character.
 *
 * @author Jenny Orell
 */
public class MutantCharacter extends PhyCharacter {

    private static final int MAX_HEALTH = 100;

    public MutantCharacter(World world){
        super(world);
    }

    public int getMaxHP(){
        return MAX_HEALTH;
    }
}
