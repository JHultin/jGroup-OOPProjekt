package edu.chl.rocc.core.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import java.util.HashMap;
import java.util.Stack;

/**
 * A class which manages the different views.
 *
 * Created by Jacob on 2015-05-04.
 */
public class GameViewManager {

    private HashMap<String, Screen> viewHashMap;
    private Screen activeView;

    public GameViewManager(IModel menuModel, IRoCCModel roccModel){
        viewHashMap = new HashMap<String,Screen>();

        /**
         *  Adds views to a HashMap so that they don't need
         *  to be creating new everytime.
         */
        viewHashMap.put("MENU", ViewFactory.createView("MENU", menuModel));
        viewHashMap.put("PLAY",ViewFactory.createView("PLAY", roccModel));

       // activeView = viewHashMap.get("MENU");
        activeView = viewHashMap.get("PLAY");
     }


    public void setActiveView(String view){
        activeView = viewHashMap.get(view);
    }

    public Screen getActiveView(){
        return activeView;
    }

}
