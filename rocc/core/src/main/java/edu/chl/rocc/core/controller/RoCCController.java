package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.options.KeyOptions;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;
import edu.chl.rocc.core.view.screens.PlayView;
import org.jbox2d.common.Vec2;

import java.util.ArrayList;

/**
 * Controllerclass for the RoCC project
 * Creates all object exept for main view and handles all input
 * and updates.
 *
 * Created by Joel on 2015-04-22.
 */
public class RoCCController implements Runnable{

    // The different moduels in the project
    private final IRoCCModel model;
    private final RoCCView main;
    private final GameViewManager gvm;
    private CollisionListener collisionListener;
    private String currentView;

    // Inputprocessor for while ingame
    private GameProcessor gameProcessor;

    // Treads and boolean to show when the tread should operate
    private Thread thread;
    private boolean isRunning = true;
    private boolean inGame;

    // Tells how far in between the world shall update, in seconds
    private float updateSpeed = 1 / 60f;

    // The current key configurations
    private KeyOptions keyOptions;

    private ViewChooser viewChooser;

    /**
     * Constructor for the controller.
     * Everything that need to exist when the game starts here.
     *
     * @param main the main view to update throughout
     */
    public RoCCController(RoCCView main){
        this.model = new PhyRoCCModel();
        this.main = main;
        this.gvm = new GameViewManager(model);

        this.gameProcessor = new GameProcessor();
        this.keyOptions = KeyOptions.getInstance();

        // Start up the game with the menuscreen
        this.gvm.setActiveView("menu");
        this.main.setScreen(this.gvm.getActiveView());
        this.inGame = false;

        // Start the thread
        this.thread = new Thread(this);
        this.thread.start();
        viewChooser = new ViewChooser(gvm.getViewObserver());

    }

    /**
     * Update the input to the correct for the current part of the game.
     * Constructs the level if selected
     *
     * @param str
     */
    public void setState(String str){
        // Tell the GameViewManager to choose the correct screen
        gvm.setActiveView(str);

        // If a game is started
        if ("game".equals(str) && !"game".equals(currentView)) {
            // Stop the thread
            /*this.isRunning = false;
            this.thread.interrupt();*/

            // Set up the game
            // First construct the world, the level and all pickupables.
            TiledMap tiledMap = new TmxMapLoader().load("tileMaps/level1-with-fin.tmx");
            ((PlayView) this.gvm.getActiveView()).setMap(tiledMap);
            this.model.constructWorld(tiledMap);

            // Add correct listener to handle the collisions in the world
            this.collisionListener = new CollisionListener();
            this.model.setCollisionListener(this.collisionListener);

            // Then create the characters
            // This should be done with the help of a profile
            this.model.addCharacter("mother");
            this.model.addCharacter("doctor");
            this.model.addCharacter("soldier");

            this.model.getPlayer().setActiveCharacter(0);

            // Restart the thread and apply correct inputprocessor

            Gdx.input.setInputProcessor(gameProcessor);
            /*this.thread = new Thread(this);
            this.thread.start();
            this.isRunning = true;*/
            this.inGame = true;

        // If we went to a menu instead
        } else if (("menu".equals(str))||("loadGame".equals(str))||
                ("options".equals(str))||("highscore".equals(str))){
            this.inGame = false;
            /*isRunning = false;
            thread.interrupt();*/
            model.dispose();
            /*thread = new Thread(this);
            thread.start();
            isRunning = true;*/
        }
        currentView = str;
        // Tell main to update to correct screen
        main.setScreen(gvm.getActiveView());
    }

    /**
     *Used to lowe memory leaking
     */
    public void dispose(){
        this.gvm.dispose();
        this.model.dispose();
    }

    /**
     *Is called as long as the thread runs, tells correct processor to update
     */
    @Override
    public void run() {
        while (this.isRunning){
            try {
                // If we're ingame use the input from the gameprocessor
                if (inGame) {
                    gameProcessor.sendUpdate();
                }
                Thread.sleep((long)(updateSpeed * 1000));
            } catch (InterruptedException e) {
                this.isRunning = false;
            }
        }
    }

    // Handles input when ingame
    private class GameProcessor implements InputProcessor{

        // Holds information on which keys that are currently pressed
        private ArrayList<Integer> keys;

        // List of all shots to make everytime the game updates
        private ArrayList<Vec2> shots;

        private GameProcessor (){
            keys = new ArrayList<Integer>();
            shots = new ArrayList<Vec2>();
        }

        // Tells the model when and how to update
        private void sendUpdate(){
            // Find correct direction for the active character to move in
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

            // Move the main character
            model.moveSideways(dir);

            // Then all other characters
            model.moveFollowers(dir);

            // Make the followers jump if they should.
            model.jumpFollowerIfPossible();

            // Do all shots cerated since last update
            for(Vec2 v : shots){
                model.shoot(v.x, v.y);
            }
            shots.clear();

            // Then update the world
            model.updateWorld(updateSpeed);

            // Lastly do all operations that triggered from the update
            // but can't be reacted to during the update
            model.removeItems(collisionListener.getItemsToRemove());
            String newState = collisionListener.getNewState();
            if (newState != null) {
                RoCCController.this.setState(newState);
            }
        }

        // Add key to keylist or jump
        @Override
        public boolean keyDown(int keycode) {
            if (keycode == keyOptions.getKey("jump"))
                model.jump();
            else if (!keys.contains(keycode))
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

            // Get the mouse-coordinates compared to the character
            double xd = screenX - Gdx.graphics.getWidth() / 2;
            double yd = Gdx.graphics.getHeight() / 2 - screenY;

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
