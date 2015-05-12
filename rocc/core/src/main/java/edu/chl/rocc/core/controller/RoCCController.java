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
import edu.chl.rocc.core.options.KeyOptions;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;
import edu.chl.rocc.core.view.ViewFactory;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;
import edu.chl.rocc.core.view.screens.PlayView;
import org.jbox2d.dynamics.Body;

import javax.swing.text.View;
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
    private final RoCCView main;
    private final GameViewManager gvm;
    private boolean inGame;
    private CollisionListener collisionListener;
    private KeyOptions keyOptions;

    private ViewChooser viewChooser;

    public RoCCController(RoCCView main){
        this.model = new PhyRoCCModel();
        this.main = main;
        this.keyOptions = KeyOptions.getInstance();

        this.gvm = new GameViewManager(model);

        this.gameProcessor = new GameProcessor();


        this.gvm.setActiveView("menu");
        this.main.setScreen(this.gvm.getActiveView());


        this.inGame = false;

        this.thread = new Thread(this);
        this.thread.start();
        viewChooser = new ViewChooser(gvm.getViewObserver());

    }

    public void setState(String str){
        gvm.setActiveView(str);

        if (str.equals("game")) {
            TiledMap tiledMap = new TmxMapLoader().load("tileMaps/level1-with-ch.tmx");
            ((PlayView) gvm.getActiveView()).setMap(tiledMap);
            model.constructWorld(tiledMap);
            this.collisionListener = new CollisionListener();
            model.setCollisionListener(this.collisionListener);
            model.addCharacter("mother");
            model.addCharacter("follow");
            model.addCharacter("bigDude");
            isRunning = false;
            thread.interrupt();
            Gdx.input.setInputProcessor(gameProcessor);
            thread = new Thread(this);
            thread.start();
            isRunning = true;
            inGame = true;
        } else if (("menu".equals(str))||("loadGame".equals(str))||
                ("options".equals(str))||("highscore".equals(str))){
            isRunning = false;
            thread.interrupt();
            thread = new Thread(this);
            thread.start();
            isRunning = true;
        }

        main.setScreen(gvm.getActiveView());
    }

    public void dispose(){
        this.gvm.dispose();
        this.model.dispose();
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
            if (keys.contains(keyOptions.getKey("right")))
                if (keys.contains(keyOptions.getKey("left")))
                    dir = Direction.NONE;
                else
                    dir = Direction.RIGHT;
            else if (keys.contains((keyOptions.getKey("left"))))
                dir = Direction.LEFT;
            else
                dir = Direction.NONE;

            model.moveSideways(dir);
            model.moveFollowers(dir);
            model.updateWorld(updateSpeed);
            model.removeBodies(collisionListener.getBodiesToRemove());
        }

        @Override
        public boolean keyDown(int keycode) {
            if (keycode == keyOptions.getKey("jump"))
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

            double xd = screenX - Gdx.graphics.getWidth() / 2;
            double yd = Gdx.graphics.getHeight() / 2 - screenY;

            double k = 1.0 / Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2));
            float x = (float)(xd * k);
            float y = (float)(yd * k);

            model.shoot(x, y);
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
     * An inner class which gets updated when a menubutton is clicked.
     */
    public class ViewChooser implements  IViewObserver{

        private IViewObservable observable;

        public ViewChooser(IViewObservable observerable){
            this.observable = observerable;
            this.observable.register(this);
        }

        @Override
        public void viewUpdated(String screen) {
            setState(screen);
            observable = gvm.getViewObserver();
            observable.register(this);
        }



    }



}
