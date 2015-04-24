package edu.chl.rocc.core.model;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;


/**
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel {

    private Level level;


    public RoCCModel(){
        level= new Level();
    }

    public void aim(int x, int y){

    }

    public void moveSideways(Direction dir){
        level.move(dir);
    }

    public int getCharacterXPos(){
        return level.getCharacterXPos();
    }

    public int getCharacterYPos(){
        return level.getCharacterYPos();
    }
}
