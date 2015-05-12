package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.physics.*;
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
        return new PhyFood(world, x, y);
    }

    @Override
    public IPickupableCharacter createPickupAbleCharacter(String name, int x, int y) {
        return new PhyPickupableCharacter(name, world, x, y);
    }
}
