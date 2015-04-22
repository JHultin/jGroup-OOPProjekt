package edu.chl.rocc.core.Model;

import com.badlogic.gdx.Input;

/**
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel {

    public RoCCModel(){
        //Level level= new Level();
    }

    public void keyPressed(int keyCode){
        if (keyCode == Input.Keys.RIGHT){
            this.moveSideways();
        } else if (keyCode == Input.Keys.LEFT){
            this.moveSideways();
        }
    }

    public void keyReleased(int keyCode){

    }

    private void moveSideways(){

    }
}
