package edu.chl.rocc.core.Model;

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


    public void moveSideways(Direction dir){
        level.move(dir);
    }

    public Sprite getSprite(){
        return level.getCharacterSprite();
    }

}
