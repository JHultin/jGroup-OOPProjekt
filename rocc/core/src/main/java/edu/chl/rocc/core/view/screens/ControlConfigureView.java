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
import edu.chl.rocc.core.model.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.utility.KeyOptions;

import java.util.HashMap;

/**
 * Created by Jacob on 2015-05-14.
 */
public class ControlConfigureView extends AbstractMenuView {

    //Options title
    private final Label titleLabel;

    //A hashMap to contain all the button and one to contain all the button names.
    private HashMap<String,TextButton> keysBindingHashMap;
    private String[] keyTitleArray;

    private TextButton moveLeftButton;
    private TextButton moveRightButton;
    private TextButton jumpButton;
    private TextButton pauseButton;
    private TextButton switchCharacterButton;

    private String keyToChange;

    private KeyOptions keyOptions;

    private TextButton defaultButton;
    private TextButton backButton;

    private boolean resize;

    public ControlConfigureView(IRoCCModel model){
        super(model);

        Gdx.input.setInputProcessor(stage);

        keyOptions = KeyOptions.getInstance();

        //Creating Options title
        //initialize the titleStyle and titleLabel
        titleLabel = new Label("Configure Controls", textStyle);
        titleLabel.setFontScale(2);


        //Initialize buttons
        createButtons();

        keyToChange = "";
        keyTitleArray = new String[]{"Move Left","Move Right","Jump","Pause","Switch Character"};


        //adds to table
        //adds title
        table.add(titleLabel).padBottom(20);
        table.row();

        Table buttonConfigureTable = new Table();

        float buttonWidth = 200;

        /*
         * Gets the title name of the button and uses them
         * as a key to retrive the right button
         */
        for(int i = 0; i<keyTitleArray.length; i++){
            buttonConfigureTable.add(new Label(keyTitleArray[i], textStyle)).right().padRight(15).padBottom(10);
            buttonConfigureTable.add(keysBindingHashMap.get(keyTitleArray[i])).width(buttonWidth).padBottom(15);
            buttonConfigureTable.row();
        }

        table.add(buttonConfigureTable);
        table.row();

        Table bottomTable = new Table();

        bottomTable.add(defaultButton).width(buttonWidth).left().pad(20);
        bottomTable.add(backButton).width(buttonWidth).right().pad(20);

        table.add(bottomTable);

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(resize) {
            stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            resize = false;
        }


        for (int i = 0; i<keyTitleArray.length; i++) {
            if (keyToChange.equals(keyTitleArray[i])) {

            } else {
                keysBindingHashMap.get(keyTitleArray[i]).setText(Input.Keys.toString(keyOptions.getKey(keyTitleArray[i])));
            }
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void show() {
       // super.show();
        Gdx.input.setInputProcessor(stage);
        System.out.println(Gdx.input.getInputProcessor());
    }

    @Override
    public void resume(){
        super.resume();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {
        resize = true;
    }

    @Override
    public void hide() {
        super.hide();
        Gdx.input.setInputProcessor(null);
    }
    public void createButtons(){
        backButton = new TextButton("Back", textButtonStyle);
        //Padding to button
        backButton.pad(10);

        defaultButton = new TextButton("Defaults", textButtonStyle);
        defaultButton.pad(10);

        keysBindingHashMap = new HashMap<String, TextButton>();

        moveLeftButton = new TextButton(Input.Keys.toString(keyOptions.getKey("Move Left"))
                , textButtonStyle);
        moveRightButton = new TextButton(Input.Keys.toString(keyOptions.getKey("Move Right"))
                , textButtonStyle);
        jumpButton = new TextButton(Input.Keys.toString(keyOptions.getKey("Jump"))
                , textButtonStyle);
        pauseButton = new TextButton(Input.Keys.toString(keyOptions.getKey("Pause"))
                , textButtonStyle);
        switchCharacterButton = new TextButton(Input.Keys.toString(keyOptions.getKey("Switch Character"))
                , textButtonStyle);



        moveLeftButton.pad(10);
        moveRightButton.pad(10);
        jumpButton.pad(10);
        pauseButton.pad(10);
        switchCharacterButton.pad(10);



        //add listener to buttons
        moveLeftButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                moveLeftButton.setText("PRESS KEY");
                keyToChange = "Move Left";
                notifyObserver("keySetter");
            }

        });

        moveRightButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                moveRightButton.setText("PRESS KEY");
                keyToChange = "Move Right";
                notifyObserver("keySetter");
            }
        });

        jumpButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                jumpButton.setText("PRESS KEY");
                keyToChange = "Jump";
                notifyObserver("keySetter");
            }

        });

        pauseButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseButton.setText("PRESS KEY");
                keyToChange = "Pause";
                notifyObserver("keySetter");
            }

        });

        switchCharacterButton.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                pauseButton.setText("PRESS KEY");
                keyToChange = "Switch Character";
                notifyObserver("keySetter");
            }

        });

        keysBindingHashMap.put("Move Left", moveLeftButton);
        keysBindingHashMap.put("Move Right", moveRightButton);
        keysBindingHashMap.put("Jump", jumpButton);
        keysBindingHashMap.put("Pause", pauseButton);
        keysBindingHashMap.put("Switch Character", switchCharacterButton);



        defaultButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                keyOptions.setToDefault();
            }
        });


        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                keyOptions.saveKeys();
                notifyObserver("options");
            }
        });

    }

    public void setKey(int keycode){
        if(!keyToChange.equals("")){
            keyOptions.setKey(keyToChange, keycode);
            keyOptions.saveKeys();
            keyToChange = "";
        }
    }
}
