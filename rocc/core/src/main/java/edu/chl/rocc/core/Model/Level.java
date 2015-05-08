package edu.chl.rocc.core.model;

import java.util.ArrayList;

import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


/**
 * Created by Yen on 2015-04-22.
 */
public class Level implements ILevel {

    private int time;
    private int score;

    private ArrayList <String> highscore;

    public Level(){

    }

    // Adds a block for the map to the world
    public void addBlock(BodyDef bDef, FixtureDef fDef){

    }

    public void updateWorld(float dt){

    }

    public World getWorld(){
        return null;
    }

    @Override
    public void addFood(IFood food) {

    }

}
