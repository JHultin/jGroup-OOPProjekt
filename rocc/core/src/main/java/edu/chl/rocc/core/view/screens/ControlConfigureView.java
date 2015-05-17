package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;

import java.util.HashMap;

/**
 * Created by Jacob on 2015-05-14.
 */
public class ControlConfigureView extends AbstractMenuView {

    //Options title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;




    private HashMap<String,TextButton> keysBindingHashMap;

    TextButton moveLeftButton;
    TextButton moveRightButton;
    TextButton jumpButton;
    TextButton shootButton;
    TextButton nextWeaponButton;
    TextButton interactButton;

    private TextButton defaultButton;






    private TextButton backButton;


    public ControlConfigureView(IRoCCModel model){
        super(model);

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Configure Controls", titleStyle);
        titleLabel.setFontScale(2);


        /**
         * Initialize buttons
         */
        createButtons();

        /**
         * adds to table
         */
        //adds title
        table.add(titleLabel).padBottom(20);
        table.row();

        float buttonWidth = 200;


        Table buttonConfigureTable = new Table();

        float padRight = 15;
        float padBottom = 10;

        buttonConfigureTable.add(new Label("Move Left", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(moveLeftButton).width(buttonWidth).padBottom(padBottom);
        buttonConfigureTable.row();
        buttonConfigureTable.add(new Label("Move Right", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(moveRightButton).width(buttonWidth).padBottom(padBottom);
        buttonConfigureTable.row();
        buttonConfigureTable.add(new Label("Jump", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(jumpButton).width(buttonWidth).padBottom(padBottom);
        buttonConfigureTable.row();
        buttonConfigureTable.add(new Label("Shoot", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(shootButton).width(buttonWidth).padBottom(padBottom);
        buttonConfigureTable.row();
        buttonConfigureTable.add(new Label("Next Weapon", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(nextWeaponButton).width(buttonWidth).padBottom(padBottom);
        buttonConfigureTable.row();
        buttonConfigureTable.add(new Label("Interact", titleStyle)).right().padRight(padRight).padBottom(padBottom);
        buttonConfigureTable.add(interactButton).width(buttonWidth).padBottom(padBottom);


        table.add(buttonConfigureTable);
        table.row();

        Table bottomTable = new Table();

        bottomTable.add(defaultButton).width(buttonWidth).left().pad(20);
        bottomTable.add(backButton).width(buttonWidth).right().pad(20);

        table.add(bottomTable);

       // table.debug();

        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
    }


    public void createButtons(){
        backButton = new TextButton("Back", textButtonStyle);
        //Padding to button
        backButton.pad(10);

        defaultButton = new TextButton("Defaults", textButtonStyle);
        defaultButton.pad(10);



        keysBindingHashMap = new HashMap<String, TextButton>();


        moveLeftButton = new TextButton("A", textButtonStyle);
        moveRightButton = new TextButton("D", textButtonStyle);
        jumpButton = new TextButton("SPACE", textButtonStyle);
        shootButton = new TextButton("MOUSE1", textButtonStyle);
        nextWeaponButton = new TextButton("Q", textButtonStyle);
        interactButton = new TextButton("E", textButtonStyle);

        moveLeftButton.pad(10);
        moveRightButton.pad(10);
        jumpButton.pad(10);
        shootButton.pad(10);
        nextWeaponButton.pad(10);
        interactButton.pad(10);


        /**
         * add listener to buttons
         */
        moveLeftButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                moveLeftButton.setText("PRESS KEY");
            }

        });

        moveRightButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                moveRightButton.setText("PRESS KEY");
            }

        });

        jumpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                jumpButton.setText("PRESS KEY");
            }

        });

        shootButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                shootButton.setText("PRESS KEY");
            }

        });

        nextWeaponButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                nextWeaponButton.setText("PRESS KEY");
            }

        });

        interactButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                interactButton.setText("PRESS KEY");
            }

        });




        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("options");
            }
        });

    }
}
