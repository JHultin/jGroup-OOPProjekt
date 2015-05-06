package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.physics.PhyPlayer;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-06.
 */
public class PhyPlayerFactory implements IPlayerFactory {

    public final World world;

    public PhyPlayerFactory(World world) {
        this.world = world;
    }

    @Override
    public IPlayer createPlayer(String name) {
        return new PhyPlayer(world);
    }
}
