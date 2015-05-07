package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;
import java.util.List;
/**
 * A abstract class which contains the common variables
 * and methods needed in the different Game views.
 * Created by Jacob on 2015-05-04.
 */
public abstract class GameView implements IViewObservable {

    //An arraylist to hold all of the observers
    protected ArrayList<IViewObserver> observerArrayList;

    // Camera following the player
    protected OrthographicCamera cam;
    // Camera showing the HUD
    protected OrthographicCamera hudCam;

    protected GameView(){

    }

    public abstract void update();
    public abstract void render(SpriteBatch batch, OrthographicCamera cam, OrthographicCamera hudCam);
    public abstract void dispose();

}
