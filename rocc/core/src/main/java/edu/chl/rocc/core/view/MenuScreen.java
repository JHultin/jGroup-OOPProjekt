package edu.chl.rocc.core.view;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import edu.chl.rocc.core.model.Variables;

/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuScreen extends GameView {

    private BitmapFont title = new BitmapFont();

    public MenuScreen(GameViewManager gsm){
        super(gsm);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        title.draw(batch,"Menu", Variables.WIDTH/2,Variables.HEIGHT/2);
        batch.end();
    }

    @Override
    public void dispose() {

    }

}
