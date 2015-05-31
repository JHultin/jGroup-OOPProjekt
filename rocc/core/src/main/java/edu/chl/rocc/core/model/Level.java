package edu.chl.rocc.core.model;

import java.util.ArrayList;
import java.util.List;
import edu.chl.rocc.core.m2phyInterfaces.*;


/**
 * Class for representing levels.
 * <br>Implements ILevel.
 *
 * Created by Yen on 2015-04-22.
 */
public class Level implements ILevel {

    private int time;
    private float timeCheck;

    private int score;
    private final List<IPickupable> pickupables;
    private final List<IJumpPoint> jumpPoints;
    private final List<IBullet> bullets;
    private final List<IEnemy> enemies;
    private final List<IFinishPoint> finishPoints;


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
    public void addBodyToDisposeList(IBody body){

    }

    @Override
    public void updateWorld(float dt){
        updateTime(dt);
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
        enemy.dispose();
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
        for (IPickupable pickupable : pickupables){
            pickupable.destroy();
        }
        for (IJumpPoint jumpPoint : jumpPoints){
            // dispose of jumpPoint
        }
        for (IEnemy enemy : enemies){
            enemy.dispose();
        }
        for (IFinishPoint finishPoint : finishPoints){
            finishPoint.dispose();
        }
    }

    @Override
    public int getTime(){
        return time;
    }

    @Override
    public void updateTime(float dt) {
        if (timeCheck < 1) {
            timeCheck += dt;
        } else {
            timeCheck -= 1;
            time++;
        }
    }
}
