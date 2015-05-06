package edu.chl.rocc.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.model.Variables;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;

//implements ApplicationListener
public class RoCCView implements ApplicationListener {
	private Texture characterTexture;
    private Texture followerTexture;
	private SpriteBatch batch;
    private IRoCCModel model;
    private RoCCController controller;
//	private float elapsed;
//  private static final float STEP = 1/60f;

    private GameViewManager gameViewManager;

    // Camera following the player
    private OrthographicCamera cam;
    // Camera showing the HUD
    private OrthographicCamera hudCam;

    //private Box2DDebugRenderer b2dr;

    @Override
	public void create () {

        model = new PhyRoCCModel();
        controller = new RoCCController(model, this);

        batch = new SpriteBatch();

        cam = new OrthographicCamera();
        cam.setToOrtho(false, Variables.WIDTH, Variables.HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, Variables.WIDTH, Variables.HEIGHT);

        gameViewManager = new GameViewManager(this);
        characterTexture = new Texture(Gdx.files.internal("characterSprite.png"));
		batch = new SpriteBatch();

        followerTexture = new Texture(Gdx.files.internal("followerSprite.png"));

        controller = new RoCCController(model, this);

        //b2dr = new Box2DDebugRenderer();

	}

	@Override
	public void resize (int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();
	}

	@Override
	public void render () {
//        elapsed += Gdx.graphics.getDeltaTime();
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);
        gameViewManager.update();
        gameViewManager.render();
        //b2dr.render(model.getLevel().getWorld(),camera.combined);

        // Set camera to follow player

        cam.position.set(new Vector2(model.getCharacterXPos(), model.getCharacterYPos()), 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

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

    //Getters
    public SpriteBatch getSpriteBatch(){
        return batch;
    }
    public OrthographicCamera getHudCam(){
        return hudCam;
    }
    public OrthographicCamera getCam(){
        return cam;
    }

    public RoCCController getController(){
        return controller;
    }

    public IRoCCModel getModel(){
        return model;
    }

}
