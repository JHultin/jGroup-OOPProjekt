package edu.chl.rocc.core.view;

/**
 * Created by Jacob on 2015-05-06.
 */
public class ViewFactory {

    public static GameView createView(int view, GameViewManager manager){
        if(view == ViewVariables.PLAY){
            return new PlayView(manager);
        }
        if(view == ViewVariables.MENU){
            return new MenuView(manager);
        }
        return null;
    }


}
