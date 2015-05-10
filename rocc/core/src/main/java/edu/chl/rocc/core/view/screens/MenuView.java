package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.Viewport;
import edu.chl.rocc.core.model.MenuModel;
import edu.chl.rocc.core.view.IModel;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;


/**
 * This class is supposed to contain all the information
 * for the main menu screen,
 * Created by Jacob on 2015-04-28.
 */
public class MenuView implements Screen, IViewObservable {

//    private SpriteBatch batch;
//   private OrthographicCamera cam;

    private BitmapFont titleFont = new BitmapFont();
   // private BitmapFont font = new BitmapFont();

    private TextButton newGame, exit;


    private String title = "Ruins of Corrosa City";

    private MenuModel menuModel;

    private ArrayList<IViewObserver> observerArrayList;


    //Test
    private Stage stage;
    private TextureAtlas textureAtlas;
    private Skin skin;
    private Table table;
    private TextButton newGameButton, loadGameButton, optionsButton,highscoreButton,exitButton;
    private TextButton.TextButtonStyle textButtonStyle;
    private Label.LabelStyle titleStyle;
    private BitmapFont font = new BitmapFont();

    private Label titleLabel;

    //Background
    private Image backgroundImage;
    private Texture backgroundTexture;


    public MenuView(IModel menuModel){
        this.menuModel = (MenuModel)menuModel;
        observerArrayList = new ArrayList<IViewObserver>();
    }


    @Override
    public void show() {
        //Instead of SpriteBatch a Stage is used.
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        //Initilizes background
        backgroundTexture =  new Texture(Gdx.files.internal("background.jpg"));
        backgroundImage = new Image(backgroundTexture);
        backgroundImage.setWidth(Gdx.graphics.getWidth());
        backgroundImage.setHeight(Gdx.graphics.getHeight());

        //The texture of the button
        textureAtlas = new TextureAtlas("button/button.pack");
        skin = new Skin(textureAtlas);

        //Instead of putting coordinates for every button we have a table which does it for us.
        table = new Table(skin);
        table.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //Create all of the buttons
        createButtons();


        /**
         * Creating title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font,Color.BLACK);
        titleLabel = new Label(title, titleStyle);
        titleLabel.setFontScale(2);

        /**
         * adds to table
         */
        //adds title
        table.add(titleLabel);
        table.row();
        //adds the button to the table
        table.add(newGameButton);
        table.row();
        table.add(loadGameButton);
        table.row();
        table.add(optionsButton);
        table.row();
        table.add(highscoreButton);
        table.row();
        table.add(exitButton);

        //Adds spacing to bottom
        for(Cell cell : table.getCells()){
            table.getCell(cell.getActor()).spaceBottom(10);
        }


        //Just to show where the cells are located.
       // table.debug();//Remove

        //Then add the table to the stage
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

    /**
     * Creating buttons
     */
    public void createButtons(){
        //Sets the button style
        textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("buttonUp");
        textButtonStyle.down = skin.getDrawable("buttonDown");
        //Moves the buttontext one pixel when pressed.
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.font = font;
        textButtonStyle.fontColor = Color.BLACK;



        newGameButton = new TextButton("New Game",textButtonStyle);
        loadGameButton = new TextButton("Load Game",textButtonStyle);
        optionsButton = new TextButton("Options", textButtonStyle);
        highscoreButton = new TextButton("Highscore",textButtonStyle);
        exitButton = new TextButton("Exit",textButtonStyle);
        //set padding on button in pixels
        newGameButton.pad(20);
        loadGameButton.pad(20);
        optionsButton.pad(20);
        highscoreButton.pad(20);
        exitButton.pad(20);

        /**
         * add listener to buttons
         */
        newGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                System.out.println("New Game");
            }
        });

        loadGameButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                System.out.println("Load Game");
            }
        });

        optionsButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                System.out.println("Options");
            }
        });

        highscoreButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                System.out.println("Highscore");
            }
        });

        exitButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                Gdx.app.exit();
            }
        });

    }//Create buttons end


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
    public void notifyObserver() {
        /**
         * Figure out what parameters the viewUpdated will take.
         */
        for(IViewObserver observer : observerArrayList){
            observer.viewUpdated();
        }
    }
}