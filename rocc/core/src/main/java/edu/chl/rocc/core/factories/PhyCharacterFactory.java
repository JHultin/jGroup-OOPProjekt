package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.physics.PhyCharacter;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-06.
 */
public class PhyCharacterFactory implements ICharacterFactory {

    private final World world;

    public PhyCharacterFactory (World world){
        this.world = world;
    }

    @Override
    public ICharacter createCharacter(String name, int x, int y) {
        return new PhyCharacter(world, x, y);
    }
}
