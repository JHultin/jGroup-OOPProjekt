package edu.chl.rocc.core.model.pure;

import java.util.ArrayList;
import java.util.List;
import edu.chl.rocc.core.model.m2phyInterfaces.*;


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
    public void addBlock(IBody body){

    }

    @Override
    public void updateWorld(float dt){
     //   updateTime();
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
    public void addBullet(IBullet bullet){
        bullets.add(bullet);
    }

    @Override
    public void removeBullet(IBullet bullet) {
        bullets.remove(bullet);
    }

    @Override
    public List<IBullet> getBullets(){
        return bullets;
    }

    @Override
    public void addJumpPoint(IJumpPoint jumpPoint){
        this.jumpPoints.add(jumpPoint);
    }

    @Override
    public void addEnemy(IEnemy enemy) {
        enemies.add(enemy);
    }

    @Override
    public List<IEnemy> getEnemies() {
        return enemies;
    }

    @Override
    public void removeEnemy(IEnemy enemy) {
        enemies.remove(enemy);
    }

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
        } else if (timeCheck == 60) {
            timeCheck = 1;
            time++;
        }
    }
}
