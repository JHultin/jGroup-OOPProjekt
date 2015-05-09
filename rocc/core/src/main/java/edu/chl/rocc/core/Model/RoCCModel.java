package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.factories.*;
import edu.chl.rocc.core.m2phyInterfaces.*;

import java.util.List;


/**
 * A class handeling the game model.
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

    /*
    * Move the character in a given direction.
    */

    public void moveSideways(Direction dir){
        player.move(dir);
    }

    /*
    * Make the character jump by changing its y-coordinate.
    */

    public void jump() {
        player.jump();
    }

    /*
    * Returns the x-coordinate of the given character.
    */
    public float getCharacterXPos(int i){
        return player.getCharacterXPos(i);
    }


    /*
    * Returns the y-coordinate of the given character.
    */
    public float getCharacterYPos(int i){
        return player.getCharacterYPos(i);
    }

    /*
    * Adds a character to the character list.
    */
    public void addCharacter(ICharacter c){
        this.player.addCharacter(c);
    }

    /*
    * Returns the level.
    */
    public ILevel getLevel(){
        return level;
    }

    /*
    * Returns the player.
    */
    public IPlayer getPlayer(){
        return this.player;
    }

    public void updateWorld(float dt){level.updateWorld(dt); }

    @Override
    public List<IFood> getFoods() {
        return level.getFoods();
    }

    @Override
    public void addFood(IFood food) {
        level.addFood(food);
    }


}
