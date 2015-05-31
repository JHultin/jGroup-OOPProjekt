package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;

/**
 * The defeatView which appears when the game is lost.
 * Created by Jacob on 2015-05-24.
 */
public class DefeatView extends AbstractMenuView{

    //Label title
    private final Label titleLabel, scoreLabel, timeLabel;
    //Buttons
    private TextButton retryLevelButton, backButton;


    public DefeatView(IRoCCModel model){
        super(model);

        //initialize the titleStyle and titleLabel
        titleLabel = new Label("You Died", textStyle);
        titleLabel.setFontScale(2);
        scoreLabel = new Label("0",textStyle);
        timeLabel = new Label("0",textStyle);

        //Initialize buttons
        createButtons();


        //adds to table
        table.add(titleLabel).padBottom(50);
        table.row();

        Table contentTable = new Table();

        Table textTable = new Table();
        textTable.add(new Label("Score: ", textStyle)).padBottom(20);
        textTable.add(scoreLabel).padRight(10).padBottom(20);
        textTable.row();
        textTable.add(new Label("Time; ", textStyle));
        textTable.add(timeLabel).padRight(10);

        contentTable.add(textTable).left();

        table.add(contentTable).padBottom(100).expandX();
        table.row();

        float buttonWidth = 150;
        Table bottomTable = new Table();
        bottomTable.add(backButton).width(buttonWidth).left().padRight(50);
        bottomTable.add(retryLevelButton).width(buttonWidth).right().padLeft(50);

        table.add(bottomTable);

        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
        scoreLabel.setText("" + model.getScore());
        timeLabel.setText("" + model.getTime());
    }

    //Creates all of the buttons and adds listeners
    private void createButtons(){
        retryLevelButton = new TextButton("Try Again", textButtonStyle);
        backButton = new TextButton("Back to Menu", textButtonStyle);

        //Padding to button
        retryLevelButton.pad(15);
        backButton.pad(15);

        retryLevelButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("game");
            }
        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("menu");
            }
        });

    }
}

