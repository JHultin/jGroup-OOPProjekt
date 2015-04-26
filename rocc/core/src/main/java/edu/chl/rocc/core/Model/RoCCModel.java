package edu.chl.rocc.core.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * A class handeling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel {

    private Level level;
    private Player player;

    public RoCCModel(){
        level = new Level();
        player = new Player();
    }

    public void aim(int x, int y){

    }

    public void moveSideways(Direction dir){
        player.move(dir);
    }

    public int getCharacterXPos(){
        return player.getCharacterXPos();
    }

    public int getCharacterYPos(){
        return player.getCharacterYPos();
    }

    public Level getLevel(){
        return level;
    }

    public void updateWorld(){
       // level.getWorld().step();
    }


}
