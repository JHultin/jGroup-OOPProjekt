package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.model.RoCCModel;

import java.util.Stack;

/**
 * A class which manages the different views.
 *
 * Created by Jacob on 2015-05-04.
 */
public class GameViewManager {

    private RoCCView view;
    private Stack<GameView> gameView;

    public GameViewManager(RoCCView view){
        this.view = view;
        gameView = new Stack<GameView>();
        pushState(ViewVariables.PLAY);
    }

    public GameViewManager(IModel menuModel, IRoCCModel roccModel){
        viewHashMap = new HashMap<String, GameView>();
        //Adds views to HashMap
        viewHashMap.put("MENU", ViewFactory.createView("MENU", menuModel));
        viewHashMap.put("PLAY",ViewFactory.createView("PLAY", roccModel));

        // activeView = viewHashMap.get("PLAY");
        activeView = viewHashMap.get("PLAY");
     }


    public void render(){
        gameView.peek().render();
    }

    public RoCCView getView(){
        return view;
    }

    public void setView(int view) {
        popState();
        pushState(view);
    }

    public void pushState(int view){
        gameView.push(getView(view));
    }

    public void popState(){
        GameView previousGameView = gameView.pop();
        previousGameView.dispose();
    }

    /**
     * Here we can add the different views
     */
    private GameView getView(int view){
        if(view == ViewVariables.PLAY){
            return new PlayView(this);
        }else if (view == ViewVariables.MENU){
            return new MenuView(this);
        }else {
            return null;
        }
    }

}
