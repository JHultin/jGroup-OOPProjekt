package edu.chl.rocc.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


import edu.chl.rocc.core.Model.RoCCModel;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.view.RoCCView;

public class RoCC implements ApplicationListener {
	Texture characterTexture;
	SpriteBatch batch;
	float elapsed;
    private RoCCModel model;
    private RoCCController controller;
    private RoCCView view;

    @Override
	public void create () {
        characterTexture = new Texture(Gdx.files.internal("characterSprite.png"));
		batch = new SpriteBatch();

        model = new RoCCModel();
        controller = new RoCCController(model, this);
        view = new RoCCView(model);
	}

	@Override
	public void resize (int width, int height) {
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
		batch.begin();

        batch.draw(characterTexture, model.getCharacterXPos(), model.getCharacterYPos());
        //view.draw(batch);

		batch.end();
	}

	@Override
	public void pause () {
	}

	@Override
	public void resume () {
	}

	@Override
	public void dispose () {
	}
}
