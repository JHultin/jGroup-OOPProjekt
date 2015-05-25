package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.model.m2phyInterfaces.IRoCCModel;

/**
 * Created by Jacob on 2015-05-11.
 */
public class LoadMenuView extends AbstractMenuView {

    //Options title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;

    private TextButton backButton;

    public LoadMenuView(IRoCCModel model){
        super(model);

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Load Game", titleStyle);
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

    @Override
    public void show() {
        super.show();
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


