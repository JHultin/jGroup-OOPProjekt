package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.view.ViewFactory;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

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


    public RoCCController(IRoCCModel model, RoCCView main, IViewObservable observable){
        this.model = model;
        this.main = main;
        gameProcessor = new GameProcessor();
        menuProcessor = new MenuProcessor(observable);

       // Gdx.input.setInputProcessor(gameProcessor);
        Gdx.input.setInputProcessor(menuProcessor);

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
            isRunning = true;
        } else if ("menu".equals(str)){
            isRunning = false;
            thread.interrupt();
            Gdx.input.setInputProcessor(menuProcessor);
            thread = new Thread(this);
            thread.start();
            isRunning = true;
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
            model.moveFollowers(dir);
            model.updateWorld(updateSpeed);
        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == Input.Keys.SPACE)
                model.jump();
            else if (!keys.contains(keycode))
                keys.add(keycode);
                //TEST
                System.out.println("Typed");
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
            model.shoot(screenX, Gdx.graphics.getHeight() - screenY);
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
    private class MenuProcessor implements InputProcessor, IViewObserver{


        private MenuProcessor (IViewObservable observable){
            observable.register(this);
        }

        @Override
        public boolean keyDown(int keycode) {
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


        @Override
        public void viewUpdated(String screen) {
            if(screen.equals("PLAY")) {
                main.setScreen(screen);
                setState("game");
            }
        }
    }
}
