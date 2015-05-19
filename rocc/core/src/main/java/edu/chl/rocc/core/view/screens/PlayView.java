package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.*;
import java.util.List;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import edu.chl.rocc.core.m2phyInterfaces.*;

import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.view.AnimationHandler;
import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

/**
 * This class is supposed to contain the
 * graphical data required for playing a level.
 * Created by Jacob on 2015-04-28.
 */
public class PlayView implements Screen,IViewObservable{


    private SpriteBatch batch;
    private OrthographicCamera cam;

    private Map<String, Texture> textures;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private IRoCCModel model;

    private ArrayList<IViewObserver> observerArrayList;

    private BitmapFont font = new BitmapFont();
    private Label.LabelStyle labelStyle;
    private Label scoreLabel;
    private Label timeLabel;

    private Stage stage;
    private Table table;

    //ANIMATION
    private HashMap<String,HashMap<String,AnimationHandler>> charactersAnimationHashMap;
    private TextureRegion textureRegion;
    //ANIMATION TEST END

    //Pause windowtest
    private Window pauseWindow;
    //pausetest

    //Profile Image hashmap
    private HashMap<String,Image> profileImageHashMap;
    private Table characterProfileTable;
    private HashMap<String,ProgressBar> healthBarHashMap;


    public PlayView(IRoCCModel model){
        this.model = model;
        batch = new SpriteBatch();
        cam = new OrthographicCamera();

        stage = new Stage();
        //Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        characterProfileTable = new Table();
        Table scoreTimeTable = new Table();

        //Initializes the text
        labelStyle = new Label.LabelStyle(font,Color.BLACK);
        scoreLabel = new Label("Score:\n1337", labelStyle);
        scoreLabel.setFontScale(1);
        timeLabel = new Label("Time:\n00:00", labelStyle);
        timeLabel.setFontScale(1);

        scoreTimeTable.add(scoreLabel).pad(15);
        scoreTimeTable.add(timeLabel).pad(15);



        //adds to table
        table.add(characterProfileTable).left().top();
        table.add(scoreTimeTable).right().expand().top();


        //Initialize healthBar and profilePics
        profileImageHashMap = new HashMap<String, Image>();
        healthBarHashMap = new HashMap<String, ProgressBar>();


        //Add table to stage
       // table.debug();
        stage.addActor(table);


        /**
         * Pause window
         */
        //Initialize the pause window and everything it contains
        createPauseWindow();
        //Add window
        // stage.addActor(pauseWindow); //pauseWindow should be added to stage when user press escape
       // pauseWindow.remove(); //used to remove pausewindow

        //END


        observerArrayList = new ArrayList<IViewObserver>();


        /*
         * Initializes the Hashmap and create temporary hashmaps which
         * are then placed in the main hashmap.
         */
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();
        addToAnimationHashMap();

        textures = new HashMap<String, Texture>();
        textures.put("food"   , new Texture(Gdx.files.internal("shaitpizza.png")));
        textures.put("bullet" , new Texture(Gdx.files.internal("bullet.png")));
        textures.put("doctor"  , new Texture(Gdx.files.internal("characters/doctor/idleLeft.png")));
        textures.put("enemy"  , new Texture(Gdx.files.internal("characters/enemy/idleLeft.png")));

    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        //Updates score and time
        scoreLabel.setText("Score:\n"+model.getScore());
        timeLabel.setText("Time:\n"+model.getTime());

        //Set camera to follow player
        cam.position.set(new Vector2(model.getCharacterXPos(), model.getCharacterYPos()), 0);
        cam.update();

        renderer.setView(cam);
        renderer.render();

        batch.begin();


        /*
         * Adds an image and healthbar for all the characters.
         * Updates the value of the HealthBar.
         */
        synchronized (model.getCharacters()) {
            for (ICharacter character : model.getCharacters()) {
                if (!profileImageHashMap.containsKey(character.getName())) {
                    profileImageHashMap.put(character.getName(),
                            new Image(new Texture(Gdx.files.internal("characters/" + character.getName() + "/profile.png"))));

                    createHealthBar(character);

                    characterProfileTable.add(profileImageHashMap.get(character.getName())).left().pad(2);
                    characterProfileTable.row();
                    characterProfileTable.add(healthBarHashMap.get(character.getName())).pad(2).width(40);
                    characterProfileTable.row();
                }
                healthBarHashMap.get(character.getName()).setValue(character.getHP());
            }
        }

        synchronized (model.getCharacters()) {
            for (ICharacter character : model.getCharacters()) {
                renderCharacter(character, delta);
            }
        }
        synchronized (model.getPickupables()) {
            for (IPickupable pickupable : model.getPickupables()) {
                batch.draw(textures.get(pickupable.getName()), pickupable.getX(), pickupable.getY());
            }
        }

        synchronized (model.getBullets()) {
            for (IBullet bullet : model.getBullets()) {
                batch.draw(textures.get("bullet"), bullet.getX(), bullet.getY());
            }

        }
        synchronized (model.getEnemies()) {
            for (IEnemy enemy : model.getEnemies()) {
                batch.draw(textures.get("enemy"), enemy.getX(), enemy.getY());
            }
        }
        batch.end();


        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        cam.viewportWidth = width;
        cam.viewportHeight = height;
        cam.update();

        stage.getViewport().update(width, height, false);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(HashMap<String,AnimationHandler> currentAnimation: charactersAnimationHashMap.values()){
            for(AnimationHandler animation : currentAnimation.values()){
                animation.getFrame().getTexture().dispose();
            }
        }

        for (Texture texture : textures.values()){
            texture.dispose();
        }
        batch.dispose();
        stage.dispose();
    }


    /**
     * Implemented Observable methods.
     */
    @Override
    public void register(IViewObserver observer) {
        observerArrayList.add(observer);
    }

    @Override
    public void unregister(IViewObserver observer) {
        observerArrayList.remove(observer);
    }

    @Override
    public void notifyObserver(String screen) {
        /**
         * Figure out what parameters the viewUpdated will take.
         */
        for(IViewObserver observer : observerArrayList){
            observer.viewUpdated(screen);
        }
    }

    public void setMap(TiledMap tMap){
        this.map = tMap;
        this.renderer = new OrthogonalTiledMapRenderer(map);
    }


    /**
     * A method which finds out which animation a character
     * will perform.
     * @param character
     * @param delta
     */
    public void renderCharacter(ICharacter character, float delta){
        charactersAnimationHashMap.get(character.getName()).get(character.getMoveState()).update();
        textureRegion = new TextureRegion(
                charactersAnimationHashMap.get(character.getName()).get(character.getMoveState()).getFrame());

        batch.draw(textureRegion, character.getX(), character.getY());
    }//renderCharacter end

    /**
     * A method which places all the animation texture in a hashMap
     */
    public void addToAnimationHashMap(){


/*
        for(ICharacter character : model.getCharacters()){
            HashMap<String,AnimationHandler> hashMap = new HashMap<String, AnimationHandler>();
            TextureRegion[] textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/"+ character.getName() + "/moveRight.png")), 34, 51)[0];
            //Right
            hashMap.put("moveRight",new AnimationHandler(textureRegions,1/4f));
            //Left
            textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/"+ character.getName() + "/moveLeft.png")), 34, 51)[0];
            hashMap.put("moveLeft",new AnimationHandler(textureRegions,1/4f));

            charactersAnimationHashMap.put(character.getName(),hashMap);
        }
*/

        //Mother animation
        HashMap<String,AnimationHandler> motherHashmap = new HashMap<String, AnimationHandler>();
        //Right
        TextureRegion[] textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/moveRight.png")), 34, 51)[0];
        motherHashmap.put("falseRIGHT",new AnimationHandler(textureRegions,1/12f));
        //Left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/moveLeft.png")), 34, 51)[0];
        motherHashmap.put("falseLEFT",new AnimationHandler(textureRegions,1/12f));
        //IdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/idleRight.png")), 34, 49)[0];
        motherHashmap.put("falseNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //IdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/idleLeft.png")), 34, 49)[0];
        motherHashmap.put("falseNONELEFT",new AnimationHandler(textureRegions,1/1f));
        //JumpRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/jumpRight.png")), 36, 49)[0];
        motherHashmap.put("trueRIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/jumpLeft.png")), 36, 49)[0];
        motherHashmap.put("trueLEFT", new AnimationHandler(textureRegions, 1 / 1f));
        //JumpIdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/jumpRight.png")), 36, 49)[0];
        motherHashmap.put("trueNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpIdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/mother/jumpLeft.png")), 36, 49)[0];
        motherHashmap.put("trueNONELEFT", new AnimationHandler(textureRegions, 1 / 1f));



        //zombie
        HashMap<String,AnimationHandler> zombieHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/moveRight.png")), 36, 50)[0];
        zombieHashmap.put("falseRIGHT",new AnimationHandler(textureRegions,1/12f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/moveLeft.png")), 36, 50)[0];
        zombieHashmap.put("falseLEFT",new AnimationHandler(textureRegions,1/12f));
        //IdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/idleRight.png")), 23, 50)[0];
        zombieHashmap.put("falseNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //IdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/idleLeft.png")), 23, 50)[0];
        zombieHashmap.put("falseNONELEFT",new AnimationHandler(textureRegions,1/1f));
        //JumpRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/jumpRight.png")), 35, 48)[0];
        zombieHashmap.put("trueRIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/jumpLeft.png")), 35, 48)[0];
        zombieHashmap.put("trueLEFT",new AnimationHandler(textureRegions,1/1f));
        //JumpIdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/jumpRight.png")), 35, 48)[0];
        zombieHashmap.put("trueNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpIdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/enemy/jumpLeft.png")), 35, 48)[0];
        zombieHashmap.put("trueNONELEFT", new AnimationHandler(textureRegions, 1 / 1f));




        //doctor
        HashMap<String,AnimationHandler> doctorHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/moveRight.png")), 24, 50)[0];
        doctorHashmap.put("falseRIGHT",new AnimationHandler(textureRegions, 1/12f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/moveLeft.png")), 24, 50)[0];
        doctorHashmap.put("falseLEFT",new AnimationHandler(textureRegions, 1/12f));
        //IdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/idleRight.png")), 24, 50)[0];
        doctorHashmap.put("falseNONERIGHT",new AnimationHandler(textureRegions, 1/1f));
        //IdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/idleLeft.png")), 24, 50)[0];
        doctorHashmap.put("falseNONELEFT",new AnimationHandler(textureRegions, 1/1f));
        //JumpRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/jumpRight.png")), 30, 49)[0];
        doctorHashmap.put("trueRIGHT",new AnimationHandler(textureRegions, 1/1f));
        //JumpLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/jumpLeft.png")), 30, 49)[0];
        doctorHashmap.put("trueLEFT",new AnimationHandler(textureRegions, 1/1f));
        //JumpIdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/jumpRight.png")), 30, 49)[0];
        doctorHashmap.put("trueNONERIGHT",new AnimationHandler(textureRegions, 1/1f));
        //JumpIdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/doctor/jumpLeft.png")), 30, 49)[0];
        doctorHashmap.put("trueNONELEFT", new AnimationHandler(textureRegions, 1/1f));



        //soldier
        HashMap<String,AnimationHandler> soldierHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/moveRight.png")), 25, 50)[0];
        soldierHashmap.put("falseRIGHT",new AnimationHandler(textureRegions,1/12f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/moveLeft.png")), 25, 50)[0];
        soldierHashmap.put("falseLEFT",new AnimationHandler(textureRegions,1/12f));
        //IdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/idleRight.png")), 24, 50)[0];
        soldierHashmap.put("falseNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //IdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/idleLeft.png")), 24, 50)[0];
        soldierHashmap.put("falseNONELEFT",new AnimationHandler(textureRegions,1/1f));
        //JumpRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/jumpRight.png")), 28, 49)[0];
        soldierHashmap.put("trueRIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/jumpLeft.png")), 28, 49)[0];
        soldierHashmap.put("trueLEFT",new AnimationHandler(textureRegions,1/1f));
        //JumpIdleRight
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/jumpRight.png")), 28, 49)[0];
        soldierHashmap.put("trueNONERIGHT",new AnimationHandler(textureRegions,1/1f));
        //JumpIdleLeft
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("characters/soldier/jumpLeft.png")), 28, 49)[0];
        soldierHashmap.put("trueNONELEFT", new AnimationHandler(textureRegions, 1 / 1f));


        charactersAnimationHashMap.put("mother",motherHashmap);
        charactersAnimationHashMap.put("enemy",zombieHashmap);
        charactersAnimationHashMap.put("doctor",doctorHashmap);
        charactersAnimationHashMap.put("soldier",soldierHashmap);
        //ANIMATION TEST END

    }//addToAnimationHashMap end

    public void createPauseWindow(){
        Window.WindowStyle pauseWindowStyle = new Window.WindowStyle();
        pauseWindowStyle.titleFont = font;
        pauseWindowStyle.titleFontColor = Color.BLACK;
        //pauseWindowStyle.background = pauseSkin.getDrawable("buttonUp");

        pauseWindow = new Window("PAUSE", pauseWindowStyle);

        //The texture of the buttons
        TextureAtlas textureAtlas = new TextureAtlas("button/defaultButton/button.pack");
        Skin pauseButtonSkin = new Skin(textureAtlas);

        //Sets the button style
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = pauseButtonSkin.getDrawable("buttonUp");
        textButtonStyle.down = pauseButtonSkin.getDrawable("buttonDown");
        //Moves the buttontext one pixel when pressed.
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;



        TextButton resumeButton = new TextButton("Resume",textButtonStyle);
        TextButton restartButton = new TextButton("Restart",textButtonStyle);
        TextButton optionsButton = new TextButton("Options", textButtonStyle);
        TextButton quitButton = new TextButton("Quit",textButtonStyle);

        /**
         * add listener to buttons
         */
        resumeButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("resume");
            }
        });

        restartButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("restart");
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("options");
            }
        });

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("quit");
            }
        });

        pauseWindow.padTop(64);

        pauseWindow.add(resumeButton).width(100).height(40).row();
        pauseWindow.add(restartButton).width(100).height(40).row();
        pauseWindow.add(optionsButton).width(100).height(40).row();
        pauseWindow.add(quitButton).width(100).height(40).row();

        //Adds spacing to bottom
        for(Cell cell : pauseWindow.getCells()){
            pauseWindow.getCell(cell.getActor()).spaceBottom(10);
        }


        pauseWindow.pack();

        pauseWindow.setPosition(stage.getWidth()/2 - pauseWindow.getWidth()/2,stage.getHeight()/2 - pauseWindow.getHeight()/2);
    }


    /**
     * Creates a healthBar and places it in a
     * HealthBarHashMap.
     * @param character
     */
    public void createHealthBar(ICharacter character){
        Skin skin = new Skin();
        Pixmap pixmap = new Pixmap(10, 10, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.WHITE);
        pixmap.fill();
        skin.add("white", new Texture(pixmap));

        TextureRegionDrawable textureBar = new TextureRegionDrawable(
                new TextureRegion(new Texture(Gdx.files.internal("button/healthBar/healthBarSkin.png"))));
        ProgressBar.ProgressBarStyle barStyle =
                new ProgressBar.ProgressBarStyle(skin.newDrawable("white", Color.DARK_GRAY), textureBar);
        barStyle.knobBefore = barStyle.knob;

        ProgressBar bar = new ProgressBar(0, character.getHP(), 0.5f, false, barStyle);
        bar.setPosition(10, 10);
        bar.setAnimateDuration(1);

        bar.setValue(character.getHP());

        healthBarHashMap.put(character.getName(),bar);
    }

}
