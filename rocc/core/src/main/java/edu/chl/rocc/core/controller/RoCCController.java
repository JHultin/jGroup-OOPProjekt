package edu.chl.rocc.core.controller;

import com.badlogic.gdx.InputProcessor;
import edu.chl.rocc.core.Model.RoCCModel;
import edu.chl.rocc.core.RoCC;

/**
 * Created by Joel on 2015-04-22.
 */
public class RoCCController {

    private final RoCCModel model;
    private final RoCC main;

    public RoCCController(RoCCModel model, RoCC main){
        this.model = model;
        this.main = main;
    }

    private class PrimaryProcessor implements InputProcessor{

        @Override
        public boolean keyDown(int keycode) {
            model.keyPressed(keycode);
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            model.keyReleased(keycode);
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
