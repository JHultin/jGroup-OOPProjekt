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

    public void keyPressed(int keyCode){
        if (keyCode == Input.Keys.RIGHT){
            this.moveSideways(Direction.RIGHT);
        } else if (keyCode == Input.Keys.LEFT){
            this.moveSideways(Direction.LEFT);
        } else if (keyCode == Input.Keys.UP){
            this.moveSideways(Direction.UP);
        } else if (keyCode == Input.Keys.DOWN){
            this.moveSideways(Direction.DOWN);
        }
    }

    public void keyReleased(int keyCode){

    }

    private void moveSideways(Direction dir){
        level.move(dir);
    }

    public Sprite getSprite(){
        return level.getCharacterSprite();
    }

}
