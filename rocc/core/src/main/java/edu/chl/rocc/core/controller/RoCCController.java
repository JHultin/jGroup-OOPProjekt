package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.rocc.core.logic.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.observers.IDeathEvent;
import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.utility.KeyOptions;
import edu.chl.rocc.core.logic.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;
import edu.chl.rocc.core.view.screens.ControlConfigureView;
import edu.chl.rocc.core.view.screens.PlayView;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;
import java.util.List;

/**
 * Controllerclass for the RoCC project
 * Creates all object exept for main view and handles all input
 * and updates.
 *
 * Created by Joel on 2015-04-22.
 */
public class RoCCController{

    // The different moduels in the project
    private final IRoCCModel model;
    private final RoCCView main;
    private final GameViewManager gvm;
    private CollisionListener collisionListener;
    private final DeathListener deathListener;
    private String currentView;

    // Inputprocessor for while ingame
    private final GameProcessor gameProcessor;
    //Inputprocessor for ConfigureControlView
    private final ConfigureControlsProcessor configureControlsProcessor;

    // Treads and boolean to show when the tread should operate
    private Thread thread;
    private boolean isRunning = true;
    private boolean inGame;

    // Tells how far in between the world shall update, in seconds
    private final float updateSpeed = 1 / 60f;

    // The current key configurations
    private final KeyOptions keyOptions;

    private final ViewChooser viewChooser;

    private TiledMap tiledMap;

    private int bulletSpawnX, bulletSpawnY;

    /**
     * Constructor for the controller.
     * Everything that need to exist when the game starts here.
     *
     * @param main the main view to update throughout
     */
    public RoCCController(RoCCView main){
        this.tiledMap = new TmxMapLoader().load("tileMaps/level1-with-fin-enemy.tmx");
        this.model = new PhyRoCCModel(tiledMap);
        this.deathListener = new DeathListener();
        this.main = main;
        this.gvm = new GameViewManager(model);

        this.gameProcessor = new GameProcessor();
        this.configureControlsProcessor = new ConfigureControlsProcessor();
        this.keyOptions = KeyOptions.getInstance();

        // Start up the game with the menuscreen
        this.gvm.setActiveView("menu");
        this.main.setScreen(this.gvm.getActiveView());
        this.inGame = false;

        this.viewChooser = new ViewChooser(gvm.getViewObserver());
    }

    public void start(){
        this.thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (RoCCController.this.isRunning){
                    try {
                        // If we're ingame use the input from the gameprocessor
                        if (inGame) {
                            gameProcessor.sendUpdate();
                        }
                        Thread.sleep((long)(updateSpeed * 1000));
                    } catch (InterruptedException e) {
                        RoCCController.this.isRunning = false;
                    }
                }
            }
        });
        this.thread.start();
    }

    /**
     * Update the input to the correct for the current part of the game.
     * Constructs the level if selected
     *
     * @param str
     */
    public void setState(String str){
        // If a game is started
        if ("game".equals(str) && !"game".equals(currentView)) {
            setToGameState(str);
        // If we went to a menu instead
        } else if("resume".equals(str)){
            Gdx.input.setInputProcessor(gameProcessor);
            gvm.getActiveView().resume();
            this.inGame = true;
        }else if (("menu".equals(str))||("loadGame".equals(str))||
                ("options".equals(str))||("highscore".equals(str))){
            // Tell the GameViewManager to choose the correct screen
            setToMenuState(str);
        } else if("configureControls".equals(str)){
            setToMenuState(str);
        }else if("keySetter".equals(str)){
            this.gvm.getActiveView().hide();
            Gdx.input.setInputProcessor(configureControlsProcessor);
        } else if("victory".equals(str)) {
            setToMenuState(str);
        } else if("defeat".equals(str)){
            setToMenuState(str);
        }
        currentView = str;
    }

    private void setToGameState(String str){
        // Tell the GameViewManager to choose the correct screen
        gvm.setActiveView(str);

        // Set up the game
        // First construct the world, the level and all pickupables.
        ((PlayView) this.gvm.getActiveView()).setMap(tiledMap);
        this.model.constructWorld(deathListener);

        // Add correct listener to handle the collisions in the world
        this.collisionListener = new CollisionListener();
        this.model.setCollisionListener(this.collisionListener);

        // Then create the characters
        // This should be done with the help of a profile
        this.model.addCharacter("mother" , deathListener);
        this.model.addCharacter("soldier", deathListener);

        this.model.setActiveCharacter(0);

        createWeapons();
        this.model.changeWeapon();

        Gdx.input.setInputProcessor(gameProcessor);

        this.inGame = true;

        this.viewChooser.setObservable(gvm.getViewObserver());
        // Tell main to update to correct screen
        this.main.setScreen(this.gvm.getActiveView());
    }

    private void setToMenuState(String str){
        this.inGame = false;
        this.model.dispose();
        this.gvm.setActiveView(str);

        this.viewChooser.setObservable(gvm.getViewObserver());
        this.main.setScreen(this.gvm.getActiveView());
    }

    /**
     *Used to lower memory leaking
     */
    public void dispose(){
        this.gvm.dispose();
        this.model.dispose();
    }

    /**
     * Create all weapons and add them to the weapon list.
     */
    private void createWeapons(){
        this.model.addWeapon("default");
        this.model.addWeapon("plasmaGun");
        this.model.addWeapon("ak-47");
    }

    // Handles input when ingame
    private class GameProcessor implements InputProcessor{

        // Holds information on which keys that are currently pressed
        private final List<Integer> keys;

        // List of all shots to make everytime the game updates
        private final List<Vec2> shots;

        private GameProcessor (){
            keys = new ArrayList<Integer>();
            shots = new ArrayList<Vec2>();
        }

        // Tells the model when and how to update
        private void sendUpdate(){
            // Find correct direction for the active character to move in
            moveUpdate();

            // Do all shots created since last update
            for(Vec2 v : shots){
                model.shoot(v.x, v.y);
            }
            shots.clear();

            // Then update the world
            model.updateWorld(updateSpeed);


            // Lastly do all operations that triggered from the update
            // but can't be reacted to during the update
            doPostWorldUpdateRemoval();

        }

        private void moveUpdate(){
            Direction dir;
            if (keys.contains(keyOptions.getKey("Move Right")))
                if (keys.contains(keyOptions.getKey("Move Left")))
                    dir = Direction.NONE;
                else
                    dir = Direction.RIGHT;
            else if (keys.contains((keyOptions.getKey("Move Left"))))
                dir = Direction.LEFT;
            else
                dir = Direction.NONE;

            // Move the main character
            model.moveSideways(dir);

            // Then all other characters
            model.moveFollowers(dir);
        }

        private void doPostWorldUpdateRemoval(){
            model.removeItems(collisionListener.getItemsToRemove());
            String newState = collisionListener.getNewState();
            if (newState != null) {
                RoCCController.this.setState(newState);
            }
            model.changeDirectionOnEnemies(collisionListener.getEnemiesToChangeDirection());

            model.removeBullets(collisionListener.getBulletsToRemove());

            for(IDeathEvent deathEvent : deathListener.getDeathsToHandle()){
                model.handleDeath(deathEvent);
            }
        }

        // Add key to keylist or jump
        @Override
        public boolean keyDown(int keycode) {
            if(keycode == keyOptions.getKey("Pause")){
                inGame = false;
                gvm.getActiveView().pause();
            }

            if (keycode == keyOptions.getKey("Jump"))
                model.jump();
            else if (keycode == keyOptions.getKey("Switch Character")){
                model.changeLead();
                model.changeWeapon();
            }else if (!keys.contains(keycode))
                keys.add(keycode);
            return false;
        }

        // Remove key from keylist
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

        // Calculates the aim and adds a shot to the shootinglist
        @Override
        public boolean touchDown(int screenX, int screenY, int pointer, int button) {

            // Get the x- and y-coordinates for the weapon where the bullets will spawn
            bulletSpawnX = model.getBulletSpawnX();
            bulletSpawnY = model.getBulletSpawnY();

            // Get the mouse-coordinates compared to the weapon
            double xd = screenX - Gdx.graphics.getWidth() / 2 - bulletSpawnX;
            double yd = Gdx.graphics.getHeight() / 2 - screenY - bulletSpawnY;

            // Make ON-base, pythagoras
            double k = 1.0 / Math.sqrt(Math.pow(xd, 2) + Math.pow(yd, 2));
            float x = (float)(xd * k);
            float y = (float)(yd * k);

            // Add the shot to the list, to make sure it isn't fired during the worlds update
            shots.add(new Vec2(x, y));
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

        // Updates the aim
        // Outdated, should probably be removed
        @Override
        public boolean mouseMoved(int screenX, int screenY) {
            return false;
        }

        @Override
        public boolean scrolled(int amount) {
            return false;
        }
    }



    private class ConfigureControlsProcessor implements InputProcessor {

        @Override
        public boolean keyDown(int keycode) {
            ((ControlConfigureView)gvm.getActiveView()).setKey(keycode);
            gvm.getActiveView().resume();
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

    /**
     * An inner class which gets updated when a menubutton is clicked.
     */
    public class ViewChooser implements  IViewObserver{

        private IViewObservable observable;

        public ViewChooser(IViewObservable observable){
            this.observable = observable;
            this.observable.register(this);
        }

        public void setObservable(IViewObservable observable){
            this.observable = observable;
            this.observable.register(this);
        }

        @Override
        public void viewUpdated(String screen) {
            setState(screen);
        }

    }

}
