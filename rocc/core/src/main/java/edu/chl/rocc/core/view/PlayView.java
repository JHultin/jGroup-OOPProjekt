package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;

/**
 * This class is supposed to contain the
 * graphical data required for playing a level.
 * Created by Jacob on 2015-04-28.
 */
public class PlayView extends GameView{

    private Texture characterTexture;
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;


    public PlayView(GameViewManager gsm){
        super(gsm);

        map = new TmxMapLoader().load("ground-map.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);

        model.constructWorld(map);

        characterTexture = new Texture(Gdx.files.internal("characterSprite.png"));
        batch = new SpriteBatch();

        //b2dr = new Box2DDebugRenderer();

    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        //b2dr.render(model.getLevel().getWorld(),camera.combined);

        //Set camera to follow player
        cam.position.set(new Vector2(model.getCharacterXPos(), model.getCharacterYPos()), 0);
        cam.update();
        batch.setProjectionMatrix(cam.combined);


        renderer.setView(cam);
        renderer.render();

        batch.begin();
        batch.draw(characterTexture, model.getCharacterXPos(), model.getCharacterYPos());
        //view.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {

    }

}
