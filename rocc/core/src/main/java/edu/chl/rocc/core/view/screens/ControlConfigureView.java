package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.options.KeyOptions;

import java.util.HashMap;

/**
 * Created by Jacob on 2015-05-14.
 */
public class ControlConfigureView extends AbstractMenuView {

    //Options title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;



    //A hashMap to contain all the button and one to contain all the button names.
    private HashMap<String,TextButton> keysBindingHashMap;
    private HashMap<String, String> keyTitleHashMap;



    private TextButton moveLeftButton;
    private TextButton moveRightButton;
    private TextButton jumpButton;
    private TextButton shootButton;
    private TextButton nextWeaponButton;
    private TextButton interactButton;

    private String currentButton;


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



        Table buttonConfigureTable = new Table();


        float buttonWidth = 200;
        /**
         * Gets the title name of the button and uses them
         * as a key to retrive the right button
         */
        for(String buttonName : keyTitleHashMap.values()){
            buttonConfigureTable.add(new Label(buttonName, titleStyle)).right().padRight(15).padBottom(10);
            buttonConfigureTable.add(keysBindingHashMap.get(buttonName)).width(buttonWidth).padBottom(15);
            buttonConfigureTable.row();
        }

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
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        if(currentButton != null) {
            for (String button : keyTitleHashMap.values()) {
                if (currentButton.equals(keyTitleHashMap.get(button))) {
                    keysBindingHashMap.get(button).setText("PRESS KEY");
                } else {
                    keysBindingHashMap.get(button).setText(Input.Keys.toString(KeyOptions.getInstance().getKey(button)));
                }
            }
        }
        //nextWeaponButton.setText(Input.Keys.toString(KeyOptions.getInstance().getKey("left")));
        //interactButton.setText(Input.Keys.toString(KeyOptions.getInstance().getKey("")));


        stage.act();
        stage.draw();
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
        keyTitleHashMap = new HashMap<String, String>();




        moveLeftButton = new TextButton(Input.Keys.toString(KeyOptions.getInstance().getKey("Move Left")), textButtonStyle);
        moveRightButton = new TextButton(Input.Keys.toString(KeyOptions.getInstance().getKey("Move Right")), textButtonStyle);
        jumpButton = new TextButton(Input.Keys.toString(KeyOptions.getInstance().getKey("Jump")), textButtonStyle);
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
               // moveLeftButton.setText("PRESS KEY");
                currentButton = "Move Left";
            }

        });

        moveRightButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
              //  moveRightButton.setText("PRESS KEY");
                currentButton = "Move Right";
            }

        });

        jumpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //jumpButton.setText("PRESS KEY");
                currentButton = "Jump";
            }

        });

        shootButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //shootButton.setText("PRESS KEY");
                currentButton = "Shoot";
            }

        });

        nextWeaponButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //nextWeaponButton.setText("PRESS KEY");
                currentButton = "Next Weapon";
            }

        });

        interactButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                //interactButton.setText("PRESS KEY");
                currentButton = "Interact";
            }

        });


        keyTitleHashMap.put("Move Left","Move Left");
        keyTitleHashMap.put("Move Right", "Move Right");
        keyTitleHashMap.put("Jump", "Jump");
      //  keyTitleHashMap.put("Shoot", "Shoot");
      //  keyTitleHashMap.put("Next Weapon", "Next Weapon");
      //  keyTitleHashMap.put("Interact", "Interact");


        keysBindingHashMap.put("Move Left", moveLeftButton);
        keysBindingHashMap.put("Move Right", moveRightButton);
        keysBindingHashMap.put("Jump", jumpButton);
      //  keysBindingHashMap.put("Shoot", shootButton);
      //  keysBindingHashMap.put("Next Weapon", nextWeaponButton);
      //  keysBindingHashMap.put("Interact", interactButton);


        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("options");
            }
        });

    }
}
