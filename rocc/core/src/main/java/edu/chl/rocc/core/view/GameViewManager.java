package edu.chl.rocc.core.view;

import edu.chl.rocc.core.RoCCView;

import java.util.Stack;

/**
 * A class which manages the different views.
 *
 * Created by Jacob on 2015-05-04.
 */
public class GameViewManager {

    private RoCCView view;
    private Stack<GameView> gameView;

    //The numbers doesn't meen anything special
    private static final int PLAY = 919123;
    private static final int MENU = 872122;

    public GameViewManager(RoCCView view){
        this.view = view;
        gameView = new Stack<GameView>();
        pushState(MENU);
    }

    public void update(){
        gameView.peek().update();
    }

    public void render(){
        gameView.peek().render();
    }

    public RoCCView getView(){
        return view;
    }


    public void setView(int view){
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
        if(view == PLAY){
            return new PlayView(this);
        }else if (view == MENU){
            return new MenuView(this);
        }else {
            return null;
        }
    }

}
