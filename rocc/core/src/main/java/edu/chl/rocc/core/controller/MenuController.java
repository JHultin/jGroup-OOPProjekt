package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.view.GameView;
import edu.chl.rocc.core.view.GameViewManager;
import edu.chl.rocc.core.view.MenuView;
import edu.chl.rocc.core.view.ViewVariables;

import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-05.
 */
public class MenuController {

    private MenuView view;
    private GameViewManager gameViewManager;

    public MenuController(MenuView view, GameViewManager gameViewManager){
        this.view = view;
        this.gameViewManager = gameViewManager;
        Gdx.input.setInputProcessor(new PrimaryProcessor());

    }

    private class PrimaryProcessor implements InputProcessor{

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.UP) {
                if(view.getCurrentItem()>0) {
                    view.setCurrentItem(view.getCurrentItem() - 1);
                    return true;
                }
                return true;
            }
            if (keycode == Input.Keys.DOWN) {
                if(view.getCurrentItem()<view.getItemLength()-1) {
                    view.setCurrentItem(view.getCurrentItem() + 1);
                    return true;
                }
                return true;
            }
            if (keycode == Input.Keys.ENTER) {
                if(view.getCurrentItem() == 0){
                    gameViewManager.setView(ViewVariables.PLAY);
                    return true;
                }
                return true;
            }else {
                return false;
            }
        }

        @Override
        public boolean keyUp(int keycode) {
            return false;
        }

        @Override
        public boolean keyTyped(char character) {
            return false;
        }

        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchUp(int screenX, int screenY, int pointer, int button) {
            return false;
        }

        @Override
        public boolean touchDragged(int screenX, int screenY, int pointer) {
            return false;
        }

        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
