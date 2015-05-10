package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

import java.util.HashMap;
import java.util.List;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;
import java.util.Map;

/**
 * This class is supposed to contain the
 * graphical data required for playing a level.
 * Created by Jacob on 2015-04-28.
 */
public class PlayView extends GameView{

    private Map<String, Texture> textures;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private IRoCCModel model;


    public PlayView(IModel model){

        this.model = (IRoCCModel)model;

        observerArrayList = new ArrayList<IViewObserver>();

        map = new TmxMapLoader().load("ground-food-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        this.model.constructWorld(map);

        textures = new HashMap<String, Texture>();
        textures.put("front" , new Texture(Gdx.files.internal("characterSprite.png")));
        textures.put("follow", new Texture(Gdx.files.internal("followerSprite.png")));
        textures.put("food"  , new Texture(Gdx.files.internal("shaitpizza.png")));
        //b2dr = new Box2DDebugRenderer();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera cam, OrthographicCamera hudCam) {
        //b2dr.render(model.getLevel().getWorld(),camera.combined);

       //Set camera to follow player
       cam.position.set(new Vector2(model.getCharacterXPos(0), model.getCharacterYPos(0)), 0);
       cam.update();

        renderer.setView(cam);
        renderer.render();

        batch.begin();

        for (ICharacter character : model.getCharacters()){
            batch.draw(textures.get(character.getName()), character.getX(), character.getY());
        }

        for (IFood food : model.getFoods()){
            batch.draw(textures.get("food"), food.getX(), food.getY());
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }

    @Override
    public void register(IViewObserver observer) {
        observerArrayList.add(observer);
    }

    @Override
    public void unregister(IViewObserver observer) {
        observerArrayList.remove(observer);
    }

    @Override
    public void notifyObserver() {
        /**
         * Figure out what parameters the viewUpdated will take.
         */
        for(IViewObserver observer : observerArrayList){
            observer.viewUpdated();
        }
    }
}
