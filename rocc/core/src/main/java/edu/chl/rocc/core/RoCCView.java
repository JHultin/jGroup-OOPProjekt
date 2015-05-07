package edu.chl.rocc.core;

import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.model.Variables;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;

import java.util.ArrayList;
import java.util.List;

//implements ApplicationListener
public class RoCCView implements ApplicationListener {

    private IRoCCModel model;
    private RoCCController controller;

	private SpriteBatch batch;
    private SpriteBatch batchFollower;
    private List<SpriteBatch> batches;
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
        batchFollower = new SpriteBatch();
        batches = new ArrayList<SpriteBatch>();
        addSpriteBatch(batch);
        addSpriteBatch(batchFollower);


        cam = new OrthographicCamera();
        cam.setToOrtho(false, Variables.WIDTH, Variables.HEIGHT);
        hudCam = new OrthographicCamera();
        hudCam.setToOrtho(false, Variables.WIDTH, Variables.HEIGHT);

        gameViewManager = new GameViewManager(this);

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

    public void addSpriteBatch(SpriteBatch sb){
        batches.add(sb);
    }

    //Getters
    public SpriteBatch getSpriteBatch(){
        return batch;
    }
    public SpriteBatch getSpriteBatchFollower(){
        return batchFollower;
    }
    public List<SpriteBatch> getSpriteBatchList(){
        return this.batches;
    }
    public SpriteBatch getSpriteBatch(int i){
        return batches.get(i);
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
