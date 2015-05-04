package edu.chl.rocc.core.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.sun.beans.editors.ColorEditor;
import edu.chl.rocc.core.model.Variables;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuView extends GameView {

    private BitmapFont titleFont = new BitmapFont();
    private BitmapFont font = new BitmapFont();

    private String title = "Ruins of Corosa City";

    //private List<String> menuList;
    private int currentItem;
    private String [] menuItem = {"New Game","Load Game","Options","Highscore","Exit"};


    public MenuView(GameViewManager gsm){
        super(gsm);
    }

    @Override
    public void update(float dt) {

    }

    @Override
    public void render() {
        batch.setProjectionMatrix(cam.combined);

        batch.begin();

        //Draw title
        titleFont.draw(batch,title, (Variables.WIDTH-width)/2,Variables.HEIGHT*0.8f);

        //Draws menu items
        for(int i = 0; i<menuItem.length; i++){
           // int width = font.getBounds(menuItem[i]).width;

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
}
