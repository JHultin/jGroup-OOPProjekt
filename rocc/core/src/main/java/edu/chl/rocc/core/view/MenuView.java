package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import edu.chl.rocc.core.controller.MenuController;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.model.Variables;


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

    //A variable to check which menuItem is selected
    private int currentItem;
    private String [] menuItem = {"New Game","Load Game","Options","Highscore","Exit"};

    public MenuView(IModel menuModel){
        this.menuModel = (MenuModel)menuModel;
        observerArrayList = new ArrayList<IViewObserver>();
    }

    @Override
    public void update() {

    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        //Draw title
        titleFont.draw(batch,title, (Variables.WIDTH)/2,Variables.HEIGHT*0.8f);

        //Draws menu items
        for(int i = 0; i<menuModel.getMenuItems().length; i++){

            //Checks if the item is selected
            if(currentItem == i){
                font.setColor(Color.RED);
            }else{
                font.setColor(Color.WHITE);
            }
            font.draw(batch, menuItem[i], Variables.WIDTH / 2, Variables.HEIGHT / 2 - 35 * i);
        }
        batch.end();
    }

    @Override
    public void dispose() {

    }

    public int getCurrentItem(){
        return currentItem;
    }

    public int getItemLength(){
        return menuItem.length;
    }

    public void setCurrentItem(int newItem){
        currentItem = newItem;
    }

}