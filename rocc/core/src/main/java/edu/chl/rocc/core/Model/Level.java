package edu.chl.rocc.core.model;

import com.badlogic.gdx.graphics.g2d.Sprite;

import java.util.ArrayList;
import com.badlogic.gdx.math.Vector2;





/**
 * Created by Yen on 2015-04-22.
 */
public class Level {


    private World world;
    private int time;
    private int score;

    private ArrayList <String> highscore;

    public Level(){
        world = new World(new Vector2(0, -9.81f)true);


    }

    public World getWorld(){
        return world;
    }

}
