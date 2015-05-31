package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.model.Level;
import org.jbox2d.dynamics.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handling the physics for levels.
 * <br>Implements ILevel.
 *
 * Created by Joel on 2015-05-03.
 */
public class PhyLevel implements ILevel {

    private final ILevel level;
    private final World world;
    private final List<IBody> bodiesNeedingToDispose;

    public PhyLevel(World world) {
        this.world = world;
        this.level = new Level();
        this.bodiesNeedingToDispose = new ArrayList<IBody>();
    }

    @Override
    public void addBodyToDisposeList(IBody body) {
        bodiesNeedingToDispose.add(body);
    }

    @Override
    public void updateWorld(float dt) {
        world.step(dt, 6, 2);
        this.level.updateWorld(dt);
    }

    @Override
    public void addPickupable(IPickupable pickup) {
        level.addPickupable(pickup);
    }

    @Override
    public void removePickupable(IPickupable pickup) {
        level.removePickupable(pickup);
    }

    @Override
    public List<IPickupable> getPickupables() {
        return level.getPickupables();
    }

    @Override
    public void addJumpPoint(IJumpPoint jumpPoint){
        level.addJumpPoint(jumpPoint);
    }

    @Override
    public List<IBullet> getBullets(){
        return this.level.getBullets();
    }

    @Override
    public void addBullet(IBullet bullet){
        this.level.addBullet(bullet);
    }

    @Override
    public void removeBullet(IBullet bullet) {
        this.level.removeBullet(bullet);
    }

    @Override
    public List<IEnemy> getEnemies(){
        return level.getEnemies();
    }

    @Override
    public void addEnemy(IEnemy enemy) {
        level.addEnemy(enemy);
    }

    @Override
    public void removeEnemy(IEnemy enemy){ level.removeEnemy(enemy); }

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
        return hash;
    }

    @Override
    public void addFinish(IFinishPoint finishPoint) {
        level.addFinish(finishPoint);
    }

    @Override
    public void dispose() {
        level.dispose();
        for (IBody body : bodiesNeedingToDispose){
            body.destroy();
        }
    }

    @Override
    public int getTime(){
        return level.getTime();
    }

    @Override
    public void updateTime(float dt){
        level.updateTime(dt);
    }
}
