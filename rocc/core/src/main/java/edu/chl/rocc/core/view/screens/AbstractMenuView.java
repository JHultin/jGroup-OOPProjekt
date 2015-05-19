package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL30;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-11.
 */
public abstract class AbstractMenuView implements Screen, IViewObservable {

    protected ArrayList<IViewObserver> observerArrayList;
    protected IRoCCModel model;

    protected Stage stage;
    protected TextureAtlas textureAtlas;
    protected Skin skin;
    protected Table table;

    //Background
    private Image backgroundImage;
    private Texture backgroundTexture;


    //TextButtonStyle
    protected BitmapFont font = new BitmapFont();
    protected TextButton.TextButtonStyle textButtonStyle;
    private boolean resize;


    protected AbstractMenuView(IRoCCModel model){
        this.model = model;
        observerArrayList = new ArrayList<IViewObserver>();

        stage = new Stage();

        //Initilizes background
        backgroundTexture =  new Texture(Gdx.files.internal("background.jpg"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setWidth(Gdx.graphics.getWidth());
        backgroundImage.setHeight(Gdx.graphics.getHeight());

        //The texture of the buttons
        textureAtlas = new TextureAtlas("button/defaultButton/button.pack");
        skin = new Skin(textureAtlas);

        //Sets the button style
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("buttonUp");
        textButtonStyle.down = skin.getDrawable("buttonDown");
        //Moves the buttontext one pixel when pressed.
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;

        //Instead of putting coordinates for every button we have a table which does it for us.
        table = new Table(skin);
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        //Just to show where the cells are located.
        // table.debug();//Remove


        stage.addActor(backgroundImage);
    }


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


    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 1, 1);
        Gdx.gl.glClear(GL30.GL_COLOR_BUFFER_BIT);

        if(resize) {
            stage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
            resize = false;
        }

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) {
        resize = true;
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
        if (stage != null)
            stage.dispose();
        if (textureAtlas != null)
            textureAtlas.dispose();
        if (skin != null)
            skin.dispose();
        if (backgroundTexture != null)
            backgroundTexture.dispose();
        if (font != null)
            font.dispose();
    }
}
