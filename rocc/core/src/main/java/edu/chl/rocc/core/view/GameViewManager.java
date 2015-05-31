package edu.chl.rocc.core.view;

import com.badlogic.gdx.Screen;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;

import java.util.HashMap;

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


        // Adds the different views to a HashMap so that they only needs to be
        // initiated once.
        viewHashMap.put("game", ViewFactory.createView("game", model));
        viewHashMap.put("menu", ViewFactory.createView("menu", model));
        viewHashMap.put("chooseLevel", ViewFactory.createView("chooseLevel", model));
        viewHashMap.put("options", ViewFactory.createView("options", model));
        viewHashMap.put("configureControls", ViewFactory.createView("configureControls", model));
        viewHashMap.put("highscore", ViewFactory.createView("highscore", model));
        viewHashMap.put("victory", ViewFactory.createView("victory", model));
        viewHashMap.put("defeat", ViewFactory.createView("defeat", model));
     }

    /**
     * Is called with the key to the requested
     * view to set it as active view.
     * @param view
     */
    public void setActiveView(String view){
        activeView = viewHashMap.get(view);
    }

    /**
     * @return the activeView
     */
    public Screen getActiveView(){
        return activeView;
    }

    public IViewObservable getViewObserver(){
        return (IViewObservable)activeView;
    }

    public void dispose(){
        for (Screen screen : viewHashMap.values()){
            screen.dispose();
        }
    }
}
