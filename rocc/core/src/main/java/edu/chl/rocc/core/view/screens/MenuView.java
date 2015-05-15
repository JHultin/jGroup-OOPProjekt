package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.options.GeneralOptions;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;


/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuView extends AbstractMenuView {

    private String title = "Ruins of Corrosa City";


    private TextButton newGameButton, loadGameButton, optionsButton,highscoreButton,exitButton;
    private Label.LabelStyle titleStyle;
    private Label titleLabel;


    public MenuView(IRoCCModel model){
        super(model);


        //Create all of the buttons
        createButtons();

        /**
         * Check GeneralOptions if fullscreen has previously been selected.
         */
        if( GeneralOptions.getInstance().getOption("isFullscreen") == 1) {
            Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
        }else{
            Gdx.graphics.setDisplayMode(720, 480, false);
        }




        /**
         * Creating title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font,Color.BLACK);
        titleLabel = new Label(title, titleStyle);
        titleLabel.setFontScale(2);

        /**
         * adds to table
         */
        //adds title
        table.add(titleLabel);
        table.row();
        //adds the button to the table
        float buttonWidth = 200;
        table.add(newGameButton).width(buttonWidth);
        table.row();
        table.add(loadGameButton).width(buttonWidth);
        table.row();
        table.add(optionsButton).width(buttonWidth);
        table.row();
        table.add(highscoreButton).width(buttonWidth);
        table.row();
        table.add(exitButton).width(buttonWidth);

        //Adds spacing to bottom
        for(Cell cell : table.getCells()){
            table.getCell(cell.getActor()).spaceBottom(10);
        }

        //Then add the table to the stage
        stage.addActor(table);
    }


    @Override
    public void show() {
        super.show();

    }

    /**
     * Creating buttons
     */
    public void createButtons(){
        newGameButton = new TextButton("New Game",textButtonStyle);
        loadGameButton = new TextButton("Load Game",textButtonStyle);
        optionsButton = new TextButton("Options", textButtonStyle);
        highscoreButton = new TextButton("Highscore",textButtonStyle);
        exitButton = new TextButton("Exit",textButtonStyle);
        //set padding on button in pixels
        newGameButton.pad(20);
        loadGameButton.pad(20);
        optionsButton.pad(20);
        highscoreButton.pad(20);
        exitButton.pad(20);



        /**
         * add listener to buttons
         */
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("game");
            }
        });

        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("loadGame");
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("options");
            }
        });

        highscoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("highscore");
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                Gdx.app.exit();
            }
        });

    }//Create buttons end

}