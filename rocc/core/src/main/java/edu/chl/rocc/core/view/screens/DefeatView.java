package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.model.m2phyInterfaces.IRoCCModel;

/**
 * Created by Jacob on 2015-05-24.
 */
public class DefeatView extends AbstractMenuView{

    //Label title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;
    private Label scoreLabel;
    private Label timeLabel;

    private TextButton nextLevelButton;
    private TextButton backButton;


    public DefeatView(IRoCCModel model){
        super(model);

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("You Died", titleStyle);
        titleLabel.setFontScale(2);

        scoreLabel = new Label("0",titleStyle);
        timeLabel = new Label("0",titleStyle);

        /**
         * Initialize buttons
         */
        createButtons();

        /**
         * adds to table
         */
        //adds title
        table.add(titleLabel).padBottom(50);
        table.row();

        Table contentTable = new Table();

        Table textTable = new Table();
        textTable.add(new Label("Score: ", titleStyle)).padBottom(20);
        textTable.add(scoreLabel).padRight(10).padBottom(20);
        textTable.row();
        textTable.add(new Label("Time; ", titleStyle));
        textTable.add(timeLabel).padRight(10);

        contentTable.add(textTable).left();

        Table killedByTable = new Table();
        killedByTable.add(new Label("Killed by: ", titleStyle));

        contentTable.add(killedByTable).right().expandX();



        table.add(contentTable).padBottom(100).expandX();


        table.row();

        float buttonWidth = 150;
        Table bottomTable = new Table();
        bottomTable.add(backButton).width(buttonWidth).left().padRight(50);
        bottomTable.add(nextLevelButton).width(buttonWidth).right().padLeft(50);

        table.add(bottomTable);

        //table.debug();
        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
//        scoreLabel.setText("" + model.getScore());
 //       timeLabel.setText("" + model.getTime());
    }


    public void createButtons(){
        nextLevelButton = new TextButton("Try Again", textButtonStyle);
        backButton = new TextButton("Back to Menu", textButtonStyle);

        //Padding to button
        nextLevelButton.pad(15);
        backButton.pad(15);

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

