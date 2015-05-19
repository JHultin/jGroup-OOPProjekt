package edu.chl.rocc.core.physics;
import edu.chl.rocc.core.m2phyInterfaces.IWorld;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Yen on 2015-05-11.
 */
public class PhyWorld implements IWorld{

    private World world;

    public PhyWorld(World world){
        this.world = world;
    }

    @Override
    public void createBody(BodyDef def) { //public Body createBody(BodyDef def){return world.createBody(def);}
        this.world.createBody(def);
    }

    @Override
    public void destroyBody(Body body){
        this.world.destroyBody(body);
    }
}
