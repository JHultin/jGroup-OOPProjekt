package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;
import edu.chl.rocc.core.view.ViewFactory;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;
import edu.chl.rocc.core.view.screens.PlayView;

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
    private final RoCCView main;
    private final GameViewManager gvm;
    private boolean inGame;


    public RoCCController(RoCCView main){
        this.model = new PhyRoCCModel();
        this.main = main;

        this.gvm = new GameViewManager(model);

        this.gameProcessor = new GameProcessor();
        this.menuProcessor = new MenuProcessor(this.gvm.getViewObserver());

        this.gvm.setActiveView("menu");
        this.main.setScreen(this.gvm.getActiveView());

        this.inGame = false;

        this.thread = new Thread(this);
        this.thread.start();

    }

    public void setState(String str){
        gvm.setActiveView(str);
        main.setScreen(gvm.getActiveView());
        if (str.equals("game")) {
            TiledMap tiledMap = new TmxMapLoader().load("ground-food-map.tmx");
            ((PlayView) gvm.getActiveView()).setMap(tiledMap);
            model.constructWorld(tiledMap);
            isRunning = false;
            thread.interrupt();
            Gdx.input.setInputProcessor(gameProcessor);
            thread = new Thread(this);
            thread.start();
            isRunning = true;
            inGame = true;
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
                if (inGame) {
                    gameProcessor.sendUpdate();
                }
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
            if (screen.equals("game"))
                setState("game");
        }
    }
}
