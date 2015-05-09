package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.model.Variables;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;


/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuView extends GameView {

    private BitmapFont titleFont = new BitmapFont();
    private BitmapFont font = new BitmapFont();

    private String title = "Ruins of Corrosa City";

    private MenuModel menuModel;

    public MenuView(IModel menuModel){
        this.menuModel = (MenuModel)menuModel;
        observerArrayList = new ArrayList<IViewObserver>();
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch, OrthographicCamera cam, OrthographicCamera hudCam) {
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        //Draw title
        titleFont.draw(batch,title, (Variables.WIDTH)/2,Variables.HEIGHT*0.8f);

        //Draws menu items
        for(int i = 0; i<menuModel.getMenuItems().length; i++){

            //Checks if the item is selected
            if(menuModel.isSelected(i)){
                font.setColor(Color.RED);
            }else{
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuModel.getMenuItems()[i], Variables.WIDTH / 2, Variables.HEIGHT / 2 - 35 * i);
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
    }}