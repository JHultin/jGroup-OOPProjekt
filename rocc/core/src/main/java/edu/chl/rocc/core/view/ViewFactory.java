package edu.chl.rocc.core.view;

import com.badlogic.gdx.Screen;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.screens.LoadMenuView;
import edu.chl.rocc.core.view.screens.MenuView;
import edu.chl.rocc.core.view.screens.OptionsMenuView;
import edu.chl.rocc.core.view.screens.PlayView;

/**
 * Created by Jacob on 2015-05-06.
 */
public class ViewFactory {

    public static Screen createView(String view, IRoCCModel model){
        if(view.equals("game")){
             return new PlayView(model);
        }
        if(view.equals("menu")){
            return new MenuView(model);
        }
        if(view.equals("loadGame")){
            return new LoadMenuView(model);
        }
        if(view.equals("options")){
            return new OptionsMenuView(model);
        }

        return null;
    }
}
