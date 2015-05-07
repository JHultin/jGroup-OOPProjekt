package edu.chl.rocc.core.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.model.RoCCModel;

import java.util.HashMap;
import java.util.Stack;

/**
 * A class which manages the different views.
 *
 * Created by Jacob on 2015-05-04.
 */
public class GameViewManager {

    private HashMap<String, GameView> viewHashMap;
    private GameView activeView;

    public GameViewManager(IModel menuModel, IRoCCModel roccModel){
        viewHashMap = new HashMap<String, GameView>();
        //Adds views to HashMap
        viewHashMap.put("MENU", ViewFactory.createView("MENU", menuModel));
        viewHashMap.put("PLAY",ViewFactory.createView("PLAY", roccModel));

       // activeView = viewHashMap.get("MENU");
        activeView = viewHashMap.get("PLAY");
     }


    public void update(){
        activeView.update();
    }

    public void render(SpriteBatch batch, OrthographicCamera cam, OrthographicCamera hudCam){
        activeView.render(batch, cam,hudCam);
    }

}
