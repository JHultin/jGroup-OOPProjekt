package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;

import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import edu.chl.rocc.core.m2phyInterfaces.*;

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

    private BitmapFont scoreFont = new BitmapFont();
    private Label.LabelStyle labelStyle;
    private Label scoreLabel;
    private Label timeLabel;

    private Stage stage;
    private Table table;

    //ANIMATION TEST
    private AnimationHandler animation;

    //ANIMATION TEST END


    public PlayView(IRoCCModel model){
        this.model = model;
        batch = new SpriteBatch();
        cam = new OrthographicCamera();

        stage = new Stage();
        Gdx.input.setInputProcessor(stage);
        table = new Table();
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Initializes the text
        labelStyle = new Label.LabelStyle(scoreFont,Color.BLACK);
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

        observerArrayList = new ArrayList<IViewObserver>();


        //ANIMATION TEST
        Texture motherTexture = new Texture(Gdx.files.internal("motherCharacter/motherMoveRight.png"));
        TextureRegion[] textureRegions = TextureRegion.split(motherTexture, 34, 51)[0];
        animation = new AnimationHandler(textureRegions,1/5f);
        //ANIMATION TEST END


        //map = new TmxMapLoader().load("ground-food-map.tmx");
        //renderer = new OrthogonalTiledMapRenderer(map);

        //this.model.constructWorld(map);

        textures = new HashMap<String, Texture>();
        textures.put("mother" , new Texture(Gdx.files.internal("motherCharacter/idleLeft.png")));
        textures.put("bigDude", new Texture(Gdx.files.internal("characterSprite.png")));
        textures.put("follow" , new Texture(Gdx.files.internal("followerSprite.png")));
        textures.put("food"   , new Texture(Gdx.files.internal("shaitpizza.png")));
        textures.put("bullet" , new Texture(Gdx.files.internal("bullet.png")));
        textures.put("enemy"  , new Texture(Gdx.files.internal("radioactiveZombieCharacter/zombieIdleLeft.png")));
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



        animation.update(delta);        //ANIMATION TEST


        batch.begin();

        for (ICharacter character : model.getCharacters()){

            if(character.getName().equals("mother")){       //Animation TEST
                batch.draw(animation.getFrame(),character.getX(), character.getY());       //Animation TEST
            }else {
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


}
