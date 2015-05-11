package edu.chl.rocc.core.view;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;

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

    public GameViewManager(IRoCCModel model){
        viewHashMap = new HashMap<String,Screen>();

        /**
         *  Adds views to a HashMap so that they don't need
         *  to be creating new everytime.
         */
        viewHashMap.put("game", ViewFactory.createView("game", model));
        viewHashMap.put("menu", ViewFactory.createView("menu", model));
        viewHashMap.put("options", ViewFactory.createView("options", model));


        activeView = viewHashMap.get("menu");
     }


    public void setActiveView(String view){
        activeView = viewHashMap.get(view);
    }

    public Screen getActiveView(){
        return activeView;
    }

    public IViewObservable getViewObserver(){
        return (IViewObservable)activeView;
    }
}
