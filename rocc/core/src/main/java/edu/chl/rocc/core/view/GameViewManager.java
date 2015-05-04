package edu.chl.rocc.core.view;

import edu.chl.rocc.core.RoCCView;

import java.util.Stack;

/**
 * A class which manage the different views.
 *
 * Created by Jacob on 2015-05-04.
 */
public class GameViewManager {

    private RoCCView view;
    private Stack<GameView> gameView;

    private static final int PLAY = 919123;
    private static final int MENU = 87;

    public GameViewManager(RoCCView view){
        this.view = view;
        gameView = new Stack<GameView>();
        pushState(PLAY);
    }

    public void update(float dt){
        gameView.peek().update(dt);
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
        GameView gv = gameView.pop();
        gv.dispose();
    }

    /**
     * Here we can add the different views
     */
    private GameView getView(int view){
        if(view == PLAY){
            return new PlayScreen(this);
        }else if (view == MENU){
            return new MenuScreen(this);
        }else {
            return null;
        }
    }

}
