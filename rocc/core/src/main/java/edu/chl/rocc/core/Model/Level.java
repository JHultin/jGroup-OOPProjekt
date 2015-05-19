package edu.chl.rocc.core.model;

import java.util.ArrayList;
import java.util.List;

import edu.chl.rocc.core.factories.IRoCCFactory;
import edu.chl.rocc.core.factories.RoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.*;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;


/**
 * Created by Yen on 2015-04-22.
 */
public class Level implements ILevel {

    private int time;
    private int timeCheck;

    private int score;
    private List<IPickupable> pickupables;
    private List<IJumpPoint> jumpPoints;
    private List<IBullet> bullets;
    private List<IEnemy> enemies;
    private List<IFinishPoint> finishPoints;
    

    private ArrayList <String> highscore;


    public Level(){
        pickupables  = new ArrayList<IPickupable>();
        jumpPoints   = new ArrayList<IJumpPoint>();
        bullets      = new ArrayList<IBullet>();
        enemies      = new ArrayList<IEnemy>();
        finishPoints = new ArrayList<IFinishPoint>();
        score = 0;
    }

    // Adds a block for the map to the world
    @Override
    public void addBlock(BodyDef bDef, FixtureDef fDef){

    }

    @Override
    public void updateWorld(float dt){
     //   updateTime();
    }

    @Override
    public World getWorld(){
        return null;
    }

    @Override
    public void addPickupable(IPickupable pickup) {
        pickupables.add(pickup);
    }

    @Override
    public void removePickupable(IPickupable pickup) {
        pickupables.remove(pickup);
    }


    @Override
    public List<IPickupable> getPickupables() {
        return pickupables;
    }

    @Override
    public void addJumpPoint(IJumpPoint jumpPoint){
        jumpPoints.add(jumpPoint);
    }

    @Override
    public void createBullet(float x, float y, float xDir, float yDir){

    }

    @Override
    public List<IBullet> getBullets(){
        return this.bullets;
    }

    @Override
    public void addBullet(IBullet bullet){
        bullets.add(bullet);
    }

    @Override
    public void removeBullet(IBullet bullet) {
        bullets.remove(bullet);
    }

    @Override
    public void addEnemy(IEnemy enemy) {
    }

    @Override
    public List<IEnemy> getEnemies() {
        return null;
    }

    @Override
    public void removeEnemy(IEnemy enemy) {

    }

    @Override
    public void setAim(float x, float y){ }

    @Override
    public void addFinish(IFinishPoint finishPoint) {
        finishPoints.add(finishPoint);
    }

    @Override
    public void dispose() {
        for (IBullet bullet : bullets){
            bullet.dispose();
        }
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public void updateTime() {
        if (timeCheck < 60) {
            timeCheck++;
        } else if (timeCheck >= 60) {
            timeCheck = 0;
            time++;
        }
    }
}
