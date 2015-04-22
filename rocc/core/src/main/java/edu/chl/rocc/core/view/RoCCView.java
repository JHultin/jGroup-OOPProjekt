package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Matrix4;
import edu.chl.rocc.core.Model.RoCCModel;

import java.util.List;

/**
 * This class updates the view.
 *
 * Created by Jacob on 2015-04-22.
 */
public class RoCCView {

    private RoCCModel model;

    private Sprite sprite;


    public RoCCView(RoCCModel model) {
        this.model = model;

        this.sprite = new Sprite(new Texture(Gdx.files.internal("libgdx-logo.png")));
    }

    public void draw(SpriteBatch batch) {

        batch.draw(sprite, sprite.getX(), sprite.getY());

    /*
        for(Sprite sprite : model.getSprites()){
            batch.draw(sprite, sprite.getX(), sprite.getY());
        }
        */
    }


}
