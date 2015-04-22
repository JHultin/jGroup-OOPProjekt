package edu.chl.rocc.core.Model;

import java.util.ArrayList;

/**
 * Created by Yen on 2015-04-22.
 */
public class Level {
    private int time;
    private int score;
    private ArrayList <String> highscore;

    public Level(){

    }

    public void move(){
        Character character = new Character();
        character.move();
    }
}
