package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-11.
 */
public class OptionsMenuView extends AbstractMenuView{

    //Options title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;

    private TextButton backButton;

    public OptionsMenuView(IRoCCModel model){
        super(model);
    }

    @Override
    public void show() {
        super.show();

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Options", titleStyle);
        titleLabel.setFontScale(2);


        /**
         * Initialize buttons
         */
        createButtons();

        /**
         * adds to table
         */
        //adds title
        table.add(titleLabel);
        table.row();

        float buttonWidth = 200;
        table.add(backButton).width(buttonWidth);

        stage.addActor(table);
    }


    public void createButtons(){
        backButton = new TextButton("Back", textButtonStyle);

        //Padding to button
        backButton.pad(20);


        /**
         * add listener to buttons
         */
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("menu");
            }
        });

    }
}
