package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.*;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import edu.chl.rocc.core.model.m2phyInterfaces.*;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.utility.CharacterTextureLoader;
import edu.chl.rocc.core.utility.WeaponTextureLoader;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

/**
 * This class is supposed to contain the
 * graphical data required for playing a level.
 * Created by Jacob on 2015-04-28.
 */
public class PlayView implements Screen,IViewObservable{

    private final SpriteBatch batch;
    private OrthographicCamera cam;

    private Map<String, Texture> textures;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private final IRoCCModel model;

    private ArrayList<IViewObserver> observerArrayList;

    private BitmapFont font = new BitmapFont();
    private Label.LabelStyle labelStyle;
    private Label scoreLabel, timeLabel;

    private Stage stage;
    private Table table;

    //ANIMATION
    private HashMap<String,HashMap<String,AnimationHandler>> charactersAnimationHashMap;
    private TextureRegion textureRegion;
    private String[] characterNames;
    private String[] currentAnimation;

    //WeaponTexture
    private String[] weaponNames;
    private String[] weaponTextures;
    private HashMap<String, HashMap<String,Texture>> weaponHashMap;

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

        //Initializes the text
        labelStyle = new Label.LabelStyle(font,Color.BLACK);
        scoreLabel = new Label("Score:", labelStyle);
        scoreLabel.setFontScale(1);
        timeLabel = new Label("Time:", labelStyle);
        timeLabel.setFontScale(1);

        characterProfileTable = new Table();
        Table scoreTimeTable = new Table();

        scoreTimeTable.add(scoreLabel).pad(15);
        scoreTimeTable.add(timeLabel).pad(15);

        //adds to table
        table.add(characterProfileTable).left().top();
        table.add(scoreTimeTable).right().expand().top();

        //Initialize healthBar and profilePics
        profileImageHashMap = new HashMap<String, Image>();
        healthBarHashMap = new HashMap<String, ProgressBar>();

        //Add table to stage
        stage.addActor(table);

        //Initialize the pause window and everything it contains
        createPauseWindow();

        observerArrayList = new ArrayList<IViewObserver>();

         // Initializes the Hashmap and create temporary hashmaps which are then placed in the main hashmap.
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();

        //Adds the different animation state names in a array
        currentAnimation = new String[]{"falseRIGHTfalse","falseLEFTfalse","falseNONERIGHTfalse","falseNONELEFTfalse"
                ,"trueRIGHTfalse","trueLEFTfalse","trueNONERIGHTfalse","trueNONELEFTfalse", "falseRIGHTtrue","falseLEFTtrue","falseNONERIGHTtrue","falseNONELEFTtrue"
                ,"trueRIGHTtrue","trueLEFTtrue","trueNONERIGHTtrue","trueNONELEFTtrue"};
        characterNames = new String[]{"mother","soldier","zombie","bigZombie","doctor","noEyes"};

        //Gets the characters and initiates their textures,
        //this has to be in show because otherwise the model.getCharacters aren't initiated.
        for(int i = 0; i<characterNames.length; i++) {
            addToAnimationHashMap(characterNames[i]);
        }

        //Adds the different weapon textures
        weaponHashMap = new HashMap<String, HashMap<String, Texture>>();
        weaponNames = new String[]{"default", "ak-47", "plasmaGun"};
        weaponTextures = new String[]{"LEFT","RIGHT","bullet"};

        for(int i = 0; i<weaponNames.length; i++){
            addToWeaponTextureHashMap(weaponNames[i]);
        }


        textures = new HashMap<String, Texture>();
        textures.put("food"   , new Texture(Gdx.files.internal("shaitpizza.png")));
        textures.put("weapon" , new Texture(Gdx.files.internal("weapons/plasmaGun/weaponLeft.png")));
        textures.put("bullet" , new Texture(Gdx.files.internal("weapons/plasmaGun/bullet.png")));
        textures.put("noEyes"  , new Texture(Gdx.files.internal("characters/noEyes/idleLeft.png")));
        textures.put("zombie"   , new Texture(Gdx.files.internal("characters/zombie/idleLeft.png")));
        textures.put("doctor", new Texture(Gdx.files.internal("characters/doctor/idleLeft.png")));
    }


    @Override
    public void show() {
        for(Image i : profileImageHashMap.values()){
            i.remove();
        }
        profileImageHashMap.clear();
        for(ProgressBar i : healthBarHashMap.values()){
            i.remove();
        }
        healthBarHashMap.clear();

        characterProfileTable.clear();
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
                charactersAnimationHashMap.get(character.getName()).get(character.getMoveState()).update();
                textureRegion = new TextureRegion(
                        charactersAnimationHashMap.get(character.getName()).get(character.getMoveState()).getFrame());

                batch.draw(textureRegion, character.getX(), character.getY());
            }
        }

        //Draws the weapon
        batch.draw(weaponHashMap.get((model.getWeapon().getName())).get(""+model.getActiveCharacter().getLastDirection())
                , model.getCharacterXPos(), model.getCharacterYPos() + 5);

        synchronized (model.getPickupables()) {
            for (IPickupable pickupable : model.getPickupables()) {
                batch.draw(textures.get(pickupable.getName()), pickupable.getX(), pickupable.getY());
            }
        }

        synchronized (model.getBullets()) {
            for (IBullet bullet : model.getBullets()) {
                batch.draw(weaponHashMap.get(model.getWeapon().getName()).get("bullet")
                        , bullet.getX(), bullet.getY());
            }

        }
        synchronized (model.getEnemies()) {
            for (IEnemy enemy : model.getEnemies()) {
                if(charactersAnimationHashMap.containsKey(enemy.getName())) {
                    charactersAnimationHashMap.get(enemy.getName()).get(enemy.getMoveState()).update();
                    textureRegion = new TextureRegion(
                            charactersAnimationHashMap.get(enemy.getName()).get(enemy.getMoveState()).getFrame());

                    batch.draw(textureRegion, enemy.getX(), enemy.getY());
                }else{
                    batch.draw(textures.get("zombie"), enemy.getX(), enemy.getY());
                }
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
        Gdx.input.setInputProcessor(stage);
        stage.addActor(pauseWindow);
    }

    @Override
    public void resume() {
        pauseWindow.remove(); //used to remove pausewindow
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


        for(Image i : profileImageHashMap.values()){
            i.remove();
        }
        profileImageHashMap.clear();
        for(ProgressBar i : healthBarHashMap.values()){
            i.remove();
        }
        healthBarHashMap.clear();

        characterProfileTable.clear();

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
            for (IViewObserver observer : observerArrayList) {
                observer.viewUpdated(screen);
            }
    }

    public void setMap(TiledMap tMap){
        this.map = tMap;
        this.renderer = new OrthogonalTiledMapRenderer(map);
    }



    /**
     * A method which places all the animation textures in a hashMap.
     */
    public void addToAnimationHashMap(String character){
        CharacterTextureLoader characterTextureLoader = new CharacterTextureLoader(character);
        HashMap<String, AnimationHandler> animationHashmap = new HashMap<String, AnimationHandler>();

        for(int i = 0; i<currentAnimation.length; i++){
            TextureRegion[] textureRegions;
            Texture texture = new Texture(Gdx.files.internal(characterTextureLoader.getCharacterTexture(
                    currentAnimation[i])));

            if(texture.getWidth() > 50){//Checks if texture contains several images and needs to split
                textureRegions = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight())[0];
            }else{
                textureRegions = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0];
            }

            animationHashmap.put(currentAnimation[i], new AnimationHandler(textureRegions, 1 / 12f));
        }

        charactersAnimationHashMap.put(character, animationHashmap);
    }//addToAnimationHashMap end

    /**
     * A method which places all the weapon textures in a hashMap.
     */
    public void addToWeaponTextureHashMap(String weapon){
        WeaponTextureLoader weaponTextureLoader = new WeaponTextureLoader(weapon);
        HashMap<String, Texture> textureHashmap = new HashMap<String, Texture>();

        for(int i = 0; i<weaponTextures.length; i++){
            Texture texture = new Texture(Gdx.files.internal(weaponTextureLoader.getCharacterTexture(weaponTextures[i])));
            textureHashmap.put(weaponTextures[i],texture);
        }

        weaponHashMap.put(weapon, textureHashmap);
    }


    public void createPauseWindow(){
        Window.WindowStyle pauseWindowStyle = new Window.WindowStyle();
        pauseWindowStyle.titleFont = font;
        pauseWindowStyle.titleFontColor = Color.BLACK;

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
                notifyObserver("game");
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
//                notifyObserver("options");
            }
        });

        quitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                resume();
                notifyObserver("menu");
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
