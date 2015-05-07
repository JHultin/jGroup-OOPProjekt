package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;

import java.util.List;

/**
 * A abstract class which contains the common variables
 * and methods needed in the different Game views.
 * Created by Jacob on 2015-05-04.
 */
public abstract class GameView {

    protected GameViewManager gameViewManager;

    protected RoCCController controller;
    protected IRoCCModel model;
    protected RoCCView view;

    protected SpriteBatch batch;
    protected SpriteBatch batchFollower;
    protected List<SpriteBatch> batches;

    // Camera following the player
    protected OrthographicCamera cam;
    // Camera showing the HUD
    protected OrthographicCamera hudCam;

    protected GameView(GameViewManager gameViewManager){
        this.gameViewManager = gameViewManager;
        view = this.gameViewManager.getView();
        model = view.getModel();
        controller = view.getController();

        /*
        for(int i=0; i<view.getSpriteBatchList().size(); i++){
            view.getSpriteBatchList();
        }
        */

        batch = view.getSpriteBatch();
        batchFollower = view.getSpriteBatchFollower();

        cam = view.getCam();
        hudCam = view.getHudCam();
    }

    public abstract void update();
    public abstract void render();
    public abstract void dispose();



}
