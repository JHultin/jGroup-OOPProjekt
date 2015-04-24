package edu.chl.rocc.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;


import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.controller.RoCCController;

public class RoCCView implements ApplicationListener {
	private Texture characterTexture;
	private SpriteBatch batch;
	private float elapsed;
    private RoCCModel model;
    private RoCCController controller;


    /*Map test*/
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    //END


    @Override
	public void create () {
        /*Map test*/
        map = new TmxMapLoader().load("map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        //END


        characterTexture = new Texture(Gdx.files.internal("characterSprite.png"));
		batch = new SpriteBatch();

        model = new RoCCModel();
        controller = new RoCCController(model, this);
	}

	@Override
	public void resize (int width, int height) {
        /*Map test*/
        camera.viewportWidth = width;
        camera.viewportHeight = height;
        camera.update();
        //END
	}

	@Override
	public void render () {
		elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        /*Map test*/
        renderer.setView(camera);
        renderer.render();
        //END


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
