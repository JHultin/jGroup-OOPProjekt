package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.physics.PhyCharacter;
import edu.chl.rocc.core.physics.PhyFood;
import edu.chl.rocc.core.physics.PhyLevel;
import edu.chl.rocc.core.physics.PhyPlayer;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-08.
 */
public class PhyRoCCFactory implements IRoCCFactory {
    private final World world;

    public PhyRoCCFactory(World world){
        this.world = world;
    }

    @Override
    public IPlayer createPlayer(String name) {
        return new PhyPlayer(this.world);
    }

    @Override
    public ILevel createLevel(String name) {
        return new PhyLevel(world);
    }

    @Override
    public ICharacter createCharacter(String name, int x, int y) {
        return new PhyCharacter(world, x, y, name);
    }

    @Override
    public IFood createFood(String name, int x, int y) {
        return new PhyFood(x, y);
    }
}
