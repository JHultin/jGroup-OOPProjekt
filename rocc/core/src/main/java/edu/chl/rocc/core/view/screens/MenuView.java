package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.fileHandlers.GeneralOptions;


/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuView extends AbstractMenuView {

    private final String title = "Ruins of Corrosa City";


    private TextButton newGameButton, loadGameButton, chooseLevelButton, optionsButton,highscoreButton,exitButton;
    private final Label titleLabel;


    public MenuView(IRoCCModel model){
        super(model);

        //Create all of the buttons
        createButtons();


        //Check GeneralOptions if fullscreen has previously been selected.
        if( GeneralOptions.getInstance().getOption("isFullscreen") == 1) {
            Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width, Gdx.graphics.getDesktopDisplayMode().height, true);
        }else{
            Gdx.graphics.setDisplayMode(720, 480, false);
        }

        //Creating title
        titleLabel = new Label(title, textStyle);
        titleLabel.setFontScale(2);

        //Adds to table
        //Adds title
        table.add(titleLabel);
        table.row();
        //Adds the buttons to the table
        float buttonWidth = 200;
        table.add(newGameButton).width(buttonWidth);
        table.row();
        table.add(chooseLevelButton).width(buttonWidth);
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
        newGameButton = new TextButton("New Game", textButtonStyle);
        //loadGameButton = new TextButton("Load Game", textButtonStyle);
        chooseLevelButton = new TextButton("Choose Level", textButtonStyle);
        optionsButton = new TextButton("Options", textButtonStyle);
        highscoreButton = new TextButton("Highscore", textButtonStyle);
        exitButton = new TextButton("Exit", textButtonStyle);
        //set padding on button in pixels
        newGameButton.pad(20);
//        loadGameButton.pad(20);
        chooseLevelButton.pad(20);
        optionsButton.pad(20);
        highscoreButton.pad(20);
        exitButton.pad(20);

        //Add listeners to buttons
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("game");
            }
        });

        /*Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("loadGame");
            }
        });*/

        chooseLevelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("chooseLevel");
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