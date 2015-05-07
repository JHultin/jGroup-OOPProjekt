package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import java.util.List;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;

/**
 * This class is supposed to contain the
 * graphical data required for playing a level.
 * Created by Jacob on 2015-04-28.
 */
public class PlayView extends GameView{

    private Texture characterTexture;
    private Texture followerTexture;
    private List<Texture> textures;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;

    private IRoCCModel model;


    public PlayView(IModel model){

        this.model = (IRoCCModel)model;

        observerArrayList = new ArrayList<IViewObserver>();

        map = new TmxMapLoader().load("ground-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        this.model.constructWorld(map);

        characterTexture = new Texture(Gdx.files.internal("characterSprite.png"));
        followerTexture = new Texture(Gdx.files.internal("followerSprite.png"));
        batch = new SpriteBatch();
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        //b2dr.render(model.getLevel().getWorld(),camera.combined);

        //Set camera to follow player
        cam.position.set(new Vector2(model.getCharacterXPos(0), model.getCharacterYPos(0)), 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        renderer.setView(cam);
        renderer.render();

        batch.begin();
        batch.draw(characterTexture, model.getCharacterXPos(0), model.getCharacterYPos(0));
        batch.draw(followerTexture, model.getCharacterXPos(1), model.getCharacterYPos(1));
        //view.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {

    }
}
