package edu.chl.rocc.core.view;

import com.badlogic.gdx.Screen;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.screens.*;

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
        if(view.equals("highscore")){
            return new HighscoreMenuView(model);
        }
        if(view.equals("configureControls")){
            return  new ControlConfigureView(model);
        }
        if(view.equals("victory")){
            return new VictoryView(model);
        }
        if(view.equals("defeat")){
            return new DefeatView(model);
        }
        return null;
    }
}
