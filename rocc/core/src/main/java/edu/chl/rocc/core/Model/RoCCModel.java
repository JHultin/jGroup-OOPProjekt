package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.factories.*;
import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.physics.PhyBullet;

import java.util.List;


/**
 * A class handling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel implements IRoCCModel {

    private ILevel level;
    private IPlayer player;


    public RoCCModel(IRoCCFactory factory){

        level = factory.createLevel("");
        player = factory.createPlayer("");
    }

    public void aim(int x, int y){

    }

    // Creates a logical box2d map mimicing the tiled-map
    public void constructWorld(TiledMap tMap){

    }

    @Override
    public void moveSideways(Direction dir){
        player.move(dir);
    }

    @Override
    public void moveFollowers(Direction dir){
        player.moveFollowers(dir);
    }

    @Override
    public void jump() {
        player.jump();
    }

    @Override
    public void shoot(){
        level.createBullet();
    }

    @Override
    public float getCharacterXPos(int i){
        return player.getCharacterXPos(i);
    }

    @Override
    public float getCharacterYPos(int i){
        return player.getCharacterYPos(i);
    }

    /*
    * Adds a character to the character list.
    */
    public void addCharacter(ICharacter c){
        this.player.addCharacter(c);
    }

    @Override
    public ILevel getLevel(){
        return level;
    }

    @Override
    public IPlayer getPlayer(){
        return this.player;
    }

    @Override
    public void updateWorld(float dt){level.updateWorld(dt); }

    @Override
    public List<IFood> getFoods() {
        return level.getFoods();
    }

    @Override
    public void addFood(IFood food) {
        level.addFood(food);
    }

    @Override
    public List<IBullet> getBullets(){
        return level.getBullets();
    }

    @Override
    public void createBullet(){
        level.createBullet();
    }

    @Override
    public List<ICharacter> getCharacters() {
        return player.getCharacters();
    }

}
