package edu.chl.rocc.core.view;

import com.badlogic.gdx.Screen;
import edu.chl.rocc.core.view.screens.MenuView;
import edu.chl.rocc.core.view.screens.PlayView;

/**
 * Created by Jacob on 2015-05-06.
 */
public class ViewFactory {

    public static Screen createView(String view, IModel model){
        if(view.equals("PLAY")){
             return new PlayView(model);
        }
        if(view.equals("MENU")){
            return new MenuView(model);
        }
        return null;
    }
}
