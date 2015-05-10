package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.model.Level;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyLevel implements ILevel {

    private final ILevel level;
    private final World world;

    public PhyLevel(World world) {
        this.world = world;
        this.level = new Level();
    }

    @Override
    public void addBlock(BodyDef bDef, FixtureDef fDef) {
        world.createBody(bDef).createFixture(fDef);
    }

    @Override
    public void updateWorld(float dt) {
        world.step(dt, 6, 2);
    }

    @Override
    public World getWorld() {
        return this.world;
    }

    @Override
    public void addFood(IFood food) {
        level.addFood(food);
    }

    @Override
    public List<IFood> getFoods() {
        return level.getFoods();
    }

    @Override
    public void removeFood(IFood food) {
        level.removeFood(food);
    }

    @Override
    public void createBullet(){
        //IBullet bullet = new PhyBullet(this.getWorld(), ...);
        //this.addBullet();
    }

    @Override
    public List<IBullet> getBullets(){
        return level.getBullets();
    }

    @Override
    public void addBullet(IBullet bullet){
        level.addBullet(bullet);
    }

    @Override
    public void removeBullet(IBullet bullet){
        level.removeBullet(bullet);
    }

    @Override
    public boolean equals(Object o){
        if (this == o){
            return true;
        } else if (o == null){
            return false;
        } else if (this.getClass() != o.getClass()){
            return false;
        } else {
            return this.hashCode() == o.hashCode();
        }
    }

    @Override
    public int hashCode(){
        int hash = 0;
        hash += world.hashCode();
        // hash += level.hashCode(); // For when level gets a hashCode
        return hash;
    }
}
