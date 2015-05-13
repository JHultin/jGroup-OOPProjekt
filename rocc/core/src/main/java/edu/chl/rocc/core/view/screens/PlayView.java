package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.*;

import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.view.AnimationHandler;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;
import java.util.Map;

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
        scoreLabel = new Label("Score:\n1337", labelStyle);
        scoreLabel.setFontScale(1);
        timeLabel = new Label("Time:\n00:00", labelStyle);
        timeLabel.setFontScale(1);


        //adds to table
        table.add(timeLabel);
        table.add(scoreLabel);
        table.setPosition(200, 220);

        //Adds spacing to bottom
        for(Cell cell : table.getCells()){
            table.getCell(cell.getActor()).pad(15);
        }



        //Add table to stage
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


        /**
         * Initializes the Hashmap and create temporary hashmaps which
         * are then placed in the main hashmap.
         */
        //ANIMATION TEST
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();
        //Mother animation
        HashMap<String,AnimationHandler> motherHashmap = new HashMap<String, AnimationHandler>();
        //Right
        TextureRegion[] textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("motherCharacter/motherMoveRight.png")), 34, 51)[0];
        motherHashmap.put("motherRight",new AnimationHandler(textureRegions,1/5f));
        //Left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("motherCharacter/motherMoveLeft.png")), 34, 51)[0];
        motherHashmap.put("motherLeft",new AnimationHandler(textureRegions,1/5f));

        //Zombie
        HashMap<String,AnimationHandler> zombieHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieMoveRight.png")), 36, 50)[0];
        zombieHashmap.put("zombieRight",new AnimationHandler(textureRegions,1/3f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieMoveLeft.png")), 36, 50)[0];
        zombieHashmap.put("zombieLeft",new AnimationHandler(textureRegions,1/3f));

        //Follow
        HashMap<String,AnimationHandler> doctorHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("doctorWhoCharacter/moveRight.png")), 24, 50)[0];
        doctorHashmap.put("doctorRight",new AnimationHandler(textureRegions,1/5f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("doctorWhoCharacter/moveLeft.png")), 24, 50)[0];
        doctorHashmap.put("doctorLeft",new AnimationHandler(textureRegions,1/5f));

        //Follow
        HashMap<String,AnimationHandler> soldierHashmap = new HashMap<String, AnimationHandler>();
        //Right
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("soldierCharacter/moveRight.png")), 25, 50)[0];
        soldierHashmap.put("soldierRight",new AnimationHandler(textureRegions,1/5f));
        //left
        textureRegions = TextureRegion.split(new Texture(Gdx.files.internal("soldierCharacter/moveLeft.png")), 25, 50)[0];
        soldierHashmap.put("soldierLeft",new AnimationHandler(textureRegions,1/5f));



        charactersAnimationHashMap.put("mother",motherHashmap);
        charactersAnimationHashMap.put("enemy",zombieHashmap);
        charactersAnimationHashMap.put("follow",doctorHashmap);
        charactersAnimationHashMap.put("bigDude",soldierHashmap);
        //ANIMATION TEST END





        //map = new TmxMapLoader().load("ground-food-map.tmx");
        //renderer = new OrthogonalTiledMapRenderer(map);

        //this.model.constructWorld(map);

        textures = new HashMap<String, Texture>();
        textures.put("mother" , new Texture(Gdx.files.internal("motherCharacter/idleLeft.png")));
        textures.put("bigDude", new Texture(Gdx.files.internal("characterSprite.png")));
        textures.put("follow" , new Texture(Gdx.files.internal("doctorWhoCharacter/idleRight.png")));
        textures.put("food"   , new Texture(Gdx.files.internal("shaitpizza.png")));
        textures.put("bullet" , new Texture(Gdx.files.internal("bullet.png")));
        textures.put("enemy"  , new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieIdleLeft.png")));
        textures.put("hatGuy" , new Texture(Gdx.files.internal("enemy.png")));
        //b2dr = new Box2DDebugRenderer();





    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(cam.combined);

        //b2dr.render(model.getLevel().getWorld(),camera.combined);

        //Set camera to follow player
        cam.position.set(new Vector2(model.getCharacterXPos(0), model.getCharacterYPos(0)), 0);
        cam.update();

        renderer.setView(cam);
        renderer.render();




       // animation.update(delta);        //ANIMATION TEST


        batch.begin();

        /**
         * An advanced for-loop going through all the
         * characters.
         */
        for (ICharacter character : model.getCharacters()) {
            //Paints the character animations


            /**
             * Checks if current character is mother and renders the
             * right figure.
             */
            if (character.getName().equals("mother")) {       //Animation TEST
                if (character.inAir() == true) {
                    if (character.getDirection().equals(Direction.LEFT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/jumpLeft.png")));
                    } else if (character.getDirection().equals(Direction.RIGHT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/jumpRight.png")));
                    } else {
                        if (character.getLastDirection().equals(Direction.LEFT)) {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/jumpLeft.png")));
                        } else {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/jumpRight.png")));
                        }
                    }
                } else if (character.getDirection().equals(Direction.RIGHT)) {
                    charactersAnimationHashMap.get("mother").get("motherRight").update(delta);
                    textureRegion = charactersAnimationHashMap.get("mother").get("motherRight").getFrame();
                } else if (character.getDirection().equals(Direction.LEFT)) {
                    charactersAnimationHashMap.get("mother").get("motherLeft").update(delta);
                    textureRegion = charactersAnimationHashMap.get("mother").get("motherLeft").getFrame();
                } else if (character.getDirection().equals(Direction.NONE)) {
                    if (character.getLastDirection().equals(Direction.LEFT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/idleLeft.png")));
                    } else {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("motherCharacter/idleRight.png")));
                    }
                }

                batch.draw(textureRegion, character.getX(), character.getY());

                /**
                 * Checks if current character is follower and renders the
                 * right figure.
                 **/
            }else if(character.getName().equals("follow")) {
                if (character.inAir() == true) {
                    if (character.getFollowerDirection().equals(Direction.LEFT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/jumpLeft.png")));
                    } else if (character.getFollowerDirection().equals(Direction.RIGHT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/jumpRight.png")));
                    } else {
                        if (character.getLastFollowerDir().equals(Direction.LEFT)) {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/jumpLeft.png")));
                        } else {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/jumpRight.png")));
                        }
                    }
                } else if (character.getFollowerDirection().equals(Direction.RIGHT)) {
                    charactersAnimationHashMap.get("follow").get("doctorRight").update(delta);
                    textureRegion = charactersAnimationHashMap.get("follow").get("doctorRight").getFrame();
                } else if (character.getFollowerDirection().equals(Direction.LEFT)) {
                    charactersAnimationHashMap.get("follow").get("doctorLeft").update(delta);
                    textureRegion = charactersAnimationHashMap.get("follow").get("doctorLeft").getFrame();
                } else if (character.getFollowerDirection().equals(Direction.NONE)) {
                    if (character.getLastFollowerDir().equals(Direction.LEFT)) {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/idleLeft.png")));
                    } else {
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("doctorWhoCharacter/idleRight.png")));
                    }
                }

                batch.draw(textureRegion, character.getX(), character.getY());

            }else if(character.getName().equals("bigDude")){
                    if (character.inAir() == true) {
                        if (character.getFollowerDirection().equals(Direction.LEFT)) {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/jumpLeft.png")));
                        } else if (character.getFollowerDirection().equals(Direction.RIGHT)) {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/jumpRight.png")));
                        } else {
                            if (character.getLastFollowerDir().equals(Direction.LEFT)) {
                                textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/jumpLeft.png")));
                            } else {
                                textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/jumpRight.png")));
                            }
                        }
                    } else if (character.getFollowerDirection().equals(Direction.RIGHT)) {
                        charactersAnimationHashMap.get("bigDude").get("soldierRight").update(delta);
                        textureRegion = charactersAnimationHashMap.get("bigDude").get("soldierRight").getFrame();
                    } else if (character.getFollowerDirection().equals(Direction.LEFT)) {
                        charactersAnimationHashMap.get("bigDude").get("soldierLeft").update(delta);
                        textureRegion = charactersAnimationHashMap.get("bigDude").get("soldierLeft").getFrame();
                    } else if (character.getFollowerDirection().equals(Direction.NONE)) {
                        if (character.getLastFollowerDir().equals(Direction.LEFT)) {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/idleLeft.png")));
                        } else {
                            textureRegion = new TextureRegion(new Texture(Gdx.files.internal("soldierCharacter/idleRight.png")));
                        }
                    }

                    batch.draw(textureRegion, character.getX(), character.getY());

                /**
                 * Checks if current character is enemy and renders the
                 * right figure.
                 **/
            }else if (character.getName().equals("enemy")) {
                if (character.getFollowerDirection().equals(Direction.RIGHT)) {
                    charactersAnimationHashMap.get("enemy").get("zombieRight").update(delta);
                    textureRegion = charactersAnimationHashMap.get("enemy").get("zombieRight").getFrame();
                } else if (character.getFollowerDirection().equals(Direction.LEFT)) {
                    charactersAnimationHashMap.get("enemy").get("zombieLeft").update(delta);
                    textureRegion = charactersAnimationHashMap.get("enemy").get("zombieLeft").getFrame();
                } else if (character.getFollowerDirection().equals(Direction.NONE)) {
                    if(character.getLastFollowerDir().equals(Direction.RIGHT)){
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieIdleRight.png")));
                    }else{
                        textureRegion = new TextureRegion(new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieIdleLeft.png")));
                    }
                }
                batch.draw(textureRegion, character.getX(), character.getY());
            } else {
               batch.draw(textures.get(character.getName()), character.getX(), character.getY());
            }
        }




        for (IPickupable pickupable : model.getPickupables()){
            batch.draw(textures.get(pickupable.getName()), pickupable.getX(), pickupable.getY());
        }

        for(IBullet bullet : model.getBullets()){
            batch.draw(textures.get("bullet"), bullet.getX(), bullet.getY());
        }
        batch.end();


        //HUD TEST
        stage.act();
        stage.draw();
        //HUD TEST END
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
      /*  for(HashMap<String,AnimationHandler> currentAnimation: charactersAnimationHashMap.values()){
            for(AnimationHandler animation : currentAnimation.values()){
               // animation.
            }
        }
        */
        textureRegion.getTexture().dispose();


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


}
