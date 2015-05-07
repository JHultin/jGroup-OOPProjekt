package edu.chl.rocc.core.view;

/**
 * Created by Jacob on 2015-05-06.
 */
public class ViewFactory {

    public static GameView createView(String view, IModel model){
        if(view.equals("PLAY")){
            return new PlayView(model);
        }
        if(view.equals("MENU")){
            return new MenuView(model);
        }
        return null;
    }
}
