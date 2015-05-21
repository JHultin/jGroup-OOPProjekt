package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.options.GeneralOptions;

/**
 * Created by Jacob on 2015-05-21.
 */
public class PlayStatsView extends AbstractMenuView{

    //PlayStatsLabel title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;
    private Label scoreLabel;
    private Label timeLabel;

    private TextButton nextLevelButton;
    private TextButton backButton;


    public PlayStatsView(IRoCCModel model){
        super(model);

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Your Stats", titleStyle);
        titleLabel.setFontScale(2);

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

        Table textTable = new Table();
        textTable.add(new Label("Score: ", titleStyle)).padBottom(20);
        textTable.add(new Label("100", titleStyle)).padRight(10).padBottom(20);
        textTable.row();
        textTable.add(new Label("Time; ", titleStyle));
        textTable.add(new Label("200", titleStyle)).padRight(10);

        table.add(textTable).padBottom(100);
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
    }


    public void createButtons(){
        nextLevelButton = new TextButton("Next Level", textButtonStyle);
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




