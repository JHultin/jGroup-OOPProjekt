package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-11.
 */
public class OptionsView implements Screen, IViewObservable{

    private IRoCCModel model;

    private ArrayList<IViewObserver> observerArrayList;


    private Stage stage;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private Table table;


    //Background
    private Image backgroundImage;
    private Texture backgroundTexture;

    //Options title
    private Label.LabelStyle titleStyle;
    private BitmapFont font = new BitmapFont();
    private Label titleLabel;

    private TextButton backButton;
    private TextButton.TextButtonStyle textButtonStyle;




    public OptionsView(IRoCCModel model){
        this.model = model;
        observerArrayList = new ArrayList<IViewObserver>();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initilizes background
        backgroundTexture =  new Texture(Gdx.files.internal("background.jpg"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setWidth(Gdx.graphics.getWidth());
        backgroundImage.setHeight(Gdx.graphics.getHeight());

        //The texture of the buttons
        textureAtlas = new TextureAtlas("button/button.pack");
        skin = new Skin(textureAtlas);

        //Instead of putting coordinates for every button we have a table which does it for us.
        table = new Table(skin);
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        /**
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Options", titleStyle);
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


        stage.addActor(backgroundImage);
        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);


        stage.act();
        stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        //Lets the view scale
        stage.getViewport().update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public void createButtons(){
        //Sets the button style
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("buttonUp");
        textButtonStyle.down = skin.getDrawable("buttonDown");
        //Moves the buttontext one pixel when pressed.
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;

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


    /**
     * Implemented Observable methods.
     */
    @Override
    public void register(IViewObserver observer) {
        observerArrayList.add(observer);
    }

    @Override
    public void unregister(IViewObserver observer) {
        observerArrayList.remove(observer);
    }

    @Override
    public void notifyObserver(String screen) {
        for(IViewObserver observer : observerArrayList){
            observer.viewUpdated(screen);
        }
    }
}
