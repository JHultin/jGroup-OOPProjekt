package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.model.Level;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyLevel implements ILevel {

    private Level level;

    @Override
    public void addBlock(BodyDef bDef, FixtureDef fDef) {

    }

    @Override
    public void updateWorld(float dt) {

    }

    @Override
    public World getWorld() {
        return null;
    }
}
