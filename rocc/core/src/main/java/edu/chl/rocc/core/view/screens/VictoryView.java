package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;

/**
 * The Victory view which appears when the game is won.
 * Created by Jacob on 2015-05-21.
 */
public class VictoryView extends AbstractMenuView{

    //Label title
    private final Label titleLabel, scoreLabel, timeLabel;

    private TextButton nextLevelButton, backButton;


    public VictoryView(IRoCCModel model){
        super(model);

        //Creating Options title
        //initialize the titleStyle and titleLabel
        titleLabel = new Label("Victory!", textStyle);
        titleLabel.setFontScale(2);

        scoreLabel = new Label("0",textStyle);
        timeLabel = new Label("0",textStyle);

        createButtons();

        //adds to table
        //adds title
        table.add(titleLabel).padBottom(50);
        table.row();

        Table textTable = new Table();
        textTable.add(new Label("Score: ", textStyle)).padBottom(20);
        textTable.add(scoreLabel).padRight(10).padBottom(20);
        textTable.row();
        textTable.add(new Label("Time: ", textStyle));
        textTable.add(timeLabel).padRight(10);

        table.add(textTable).padBottom(100);
        table.row();

        float buttonWidth = 150;
        Table bottomTable = new Table();
        bottomTable.add(backButton).width(buttonWidth).left().padRight(50);
        bottomTable.add(nextLevelButton).width(buttonWidth).right().padLeft(50);

        table.add(bottomTable);

        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
        scoreLabel.setText("" + model.getScore());
        timeLabel.setText("" + model.getTime());
    }


    public void createButtons(){
        nextLevelButton = new TextButton("Next Level", textButtonStyle);
        backButton = new TextButton("Back to Menu", textButtonStyle);

        //Padding to button
        nextLevelButton.pad(15);
        backButton.pad(15);

        //add listener to buttons
        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("menu");
            }
        });
    }
}
