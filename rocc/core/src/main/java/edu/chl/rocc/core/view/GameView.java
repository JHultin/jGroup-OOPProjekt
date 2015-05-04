package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.model.RoCCModel;

/**
 * Created by Jacob on 2015-05-04.
 */
public abstract class GameView {

    protected GameViewManager gameViewManager;


    protected RoCCController controller;
    protected RoCCModel model;
    protected RoCCView view;

    protected SpriteBatch batch;

    // Camera following the player
    protected OrthographicCamera cam;
    // Camera showing the HUD
    protected OrthographicCamera hudCam;

    protected GameView(GameViewManager gameViewManager){
        this.gameViewManager = gameViewManager;
        view = this.gameViewManager.getView();
        model = view.getModel();
        controller = view.getController();
        batch = view.getSpriteBatch();
        cam = view.getCam();
        hudCam = view.getHudCam();
    }

    public abstract void update(float dt);
    public abstract void render();
    public abstract void dispose();



}
