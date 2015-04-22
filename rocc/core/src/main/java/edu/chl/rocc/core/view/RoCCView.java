package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;

import java.util.List;

/**
 * Created by Jacob on 2015-04-22.
 */
public class RoCCView{

    private Batch batch;

    public RoCCView(){

    }

    public void draw(List<Sprite> spriteList){
        for(Sprite sprite : spriteList){
            batch.draw(sprite, sprite.getX(), sprite.getY());
        }
    }



}
