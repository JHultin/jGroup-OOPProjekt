package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.view.MenuView;

import java.util.ArrayList;

/**
 * Created by Joel on 2015-04-22.
 */
public class RoCCController implements Runnable{

    private final IRoCCModel model;
    private Thread thread;
    private boolean isRunning = true;
    private float updateSpeed = 1 / 60f;
    private GameProcessor gameProcessor;
    private MenuProcessor menuProcessor;
    private RoCCView main;
    private MenuModel menuModel;


    public RoCCController(IRoCCModel model,MenuModel menuModel, RoCCView main){
        this.model = model;
        this.main = main;
        this.menuModel = menuModel;
        gameProcessor = new GameProcessor();
        menuProcessor = new MenuProcessor();

          Gdx.input.setInputProcessor(gameProcessor);
       // Gdx.input.setInputProcessor(menuProcessor);

        thread = new Thread(this);
        thread.start();

    }

    public void setState(String str){
        if (str.equals("game")) {
            isRunning = false;
            thread.interrupt();
            Gdx.input.setInputProcessor(gameProcessor);
            thread = new Thread(this);
            thread.start();
        } else if ("menu".equals(str)){
            isRunning = false;
            thread.interrupt();
            Gdx.input.setInputProcessor(menuProcessor);
            thread = new Thread(this);
            thread.start();
        }
    }

    @Override
    public void run() {
        while (this.isRunning){
            try {
                gameProcessor.sendUpdate();
                Thread.sleep((long)(updateSpeed * 1000));
            } catch (InterruptedException e) {
                this.isRunning = false;
            }
        }
    }

    private class GameProcessor implements InputProcessor{

        private ArrayList<Integer> keys;
        private Direction lastDir;

        private GameProcessor (){

            this.lastDir = Direction.NONE;
            keys = new ArrayList<Integer>();
        }

        private void sendUpdate(){
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
            if (dir != lastDir){
                model.moveSideways(dir);
                lastDir = dir;
            }
            model.updateWorld(updateSpeed);
        }


        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.SPACE)
                model.jump();
            else if (!keys.contains(keycode))
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

    /**
     * An inner class to handle the menu input.
     */
    private class MenuProcessor implements InputProcessor{

        private MenuProcessor (){

        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.ENTER) {
                menuModel.select();
                return true;
            }else if (keycode == Input.Keys.UP){
                menuModel.navigate(Direction.UP);
                return true;
            }else if (keycode == Input.Keys.DOWN){
                menuModel.navigate(Direction.DOWN);
                return true;
            }
            return false;
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
