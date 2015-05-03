package edu.chl.rocc.core.m2phyInterfaces;

import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-03.
 */
public interface ILevel {

    public void addBlock(BodyDef bDef, FixtureDef fDef);

    public void updateWorld(float dt);

    public World getWorld();
}
