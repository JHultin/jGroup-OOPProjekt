package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.RoCCView;

import java.util.ArrayList;

/**
 * Created by Joel on 2015-04-22.
 */
public class RoCCController implements Runnable{

    private final RoCCModel model;
    private Thread thread;
    private ArrayList<Integer> keys;
    private boolean isRunning = true;
    private float updateSpeed = 1 / 60f;
    private Direction lastDir;

    public RoCCController(RoCCModel model, RoCCView main){
        this.model = model;
        Gdx.input.setInputProcessor(new PrimaryProcessor());
        this.lastDir = Direction.NONE;

        keys = new ArrayList<Integer>();
        thread = new Thread(this);
        thread.start();

    }

    @Override
    public void run() {
        while (this.isRunning){
            try {
                Direction dir;
                if (keys.contains(Input.Keys.RIGHT))
                    if (keys.contains(Input.Keys.LEFT))
                        dir = Direction.NONE;
                    else
                        dir = Direction.RIGHT;
                else if (keys.contains((Input.Keys.LEFT)))
                    dir = Direction.LEFT;
                else
                    dir = Direction.NONE;
                //if (dir != lastDir){
                    model.moveSideways(dir);
                    lastDir = dir;
                //}
                /*
                for (int key : keys){
                    if (key == Input.Keys.RIGHT){
                        model.moveSideways(Direction.RIGHT);
                    } else if (key == Input.Keys.LEFT){
                        model.moveSideways(Direction.LEFT);
                    } else if (key == Input.Keys.UP){
                        model.moveSideways(Direction.UP);
                    } else if (key == Input.Keys.DOWN){
                        model.moveSideways(Direction.DOWN);
                    }
                }
                */
                model.updateWorld(updateSpeed);
                Thread.sleep((long)(updateSpeed * 1000));
            } catch (InterruptedException e) {
                this.isRunning = false;
            }
        }
    }

    private class PrimaryProcessor implements InputProcessor{

        @Override
        public boolean keyDown(int keycode) {
            if (!keys.contains(keycode))
                keys.add(keycode);
            return false;
        }

        @Override
        public boolean keyUp(int keycode) {
            if (keys.contains(keycode))
                keys.remove((Integer)keycode);
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
            model.aim(screenX, Gdx.graphics.getHeight() - screenY);
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }
}
