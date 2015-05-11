package edu.chl.rocc.core.model;

import java.util.ArrayList;
import java.util.List;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;

import edu.chl.rocc.core.m2phyInterfaces.IEnemy;
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
    private ArrayList<IFood> foods;
    private ArrayList<IBullet> bullets;
    private ArrayList<IEnemy> enemies;

    private ArrayList <String> highscore;

    public Level(){
        foods = new ArrayList<IFood>();
        bullets = new ArrayList<IBullet>();
        enemies = new ArrayList<IEnemy>();
    }

    // Adds a block for the map to the world
    @Override
    public void addBlock(BodyDef bDef, FixtureDef fDef){

    }

    @Override
    public void updateWorld(float dt){

    }

    @Override
    public World getWorld(){
        return null;
    }

    @Override
    public void addFood(IFood food) {
        foods.add(food);
    }

    @Override
    public List<IFood> getFoods() {
        return foods;
    }

    @Override
    public void removeFood(IFood food) {
        foods.remove(food);
    }

    @Override
    public void createBullet(float x, float y){

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

}
