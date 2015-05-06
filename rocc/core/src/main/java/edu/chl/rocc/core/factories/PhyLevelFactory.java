package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.physics.PhyLevel;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-06.
 */
public class PhyLevelFactory implements ILevelFactory {

    private final World world;

    public PhyLevelFactory(World world){
        this.world = world;
    }

    @Override
    public ILevel createLevel(String name) {
        return new PhyLevel();
    }
}
