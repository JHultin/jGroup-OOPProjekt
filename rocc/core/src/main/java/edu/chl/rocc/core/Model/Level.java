package edu.chl.rocc.core.Model;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;

/**
 * Created by Yen on 2015-04-22.
 */
public class Level {
    private int time;
    private int score;
    private ArrayList <String> highscore;

    private Character character;

    public Level(){

        character = new Character();
    }

    public void move(Direction dir){
        character.move(dir);
    }


    public int getCharacterXPos(){
        return character.getX();
    }

    public int getCharacterYPos(){
        return character.getY();
    }
}
