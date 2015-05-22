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
import edu.chl.rocc.core.options.GeneralOptions;
import edu.chl.rocc.core.view.observers.IViewObservable;
import edu.chl.rocc.core.view.observers.IViewObserver;

import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-11.
 */
public class OptionsMenuView extends AbstractMenuView{

    //Options title
    private Label.LabelStyle titleStyle;
    private Label titleLabel;

    private TextButton backButton;
    private TextButton controlsButton;
    private CheckBox fullscreenCheckBox;
    private Slider soundSlider;
    private Label soundVolumeLabel;
    private Label musicVolumeLabel;
    private Slider musicSlider;


    private GeneralOptions generalOptions;

    public OptionsMenuView(IRoCCModel model){
        super(model);

        generalOptions = GeneralOptions.getInstance();

        /*
         * Creating Options title
         */
        //initialize the titleStyle and titleLabel
        titleStyle = new Label.LabelStyle(font, Color.BLACK);
        titleLabel = new Label("Options", titleStyle);
        titleLabel.setFontScale(2);


        /*
         * Initialize buttons
         */
        createButtons();

        //adds title to table
        table.add(titleLabel).spaceBottom(70);
        table.row();

        Table screenControlTable = new Table();
        screenControlTable.add(fullscreenCheckBox).padRight(50);
        screenControlTable.add(controlsButton).padLeft(50);


        table.add(screenControlTable).padBottom(50);
        table.row();

        Table sliderTable = new Table();
        sliderTable.setBounds(0,0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        sliderTable.add(soundVolumeLabel).padRight(10).top();
        sliderTable.add(soundSlider).width(250);
        sliderTable.row();
        sliderTable.add(musicVolumeLabel).padRight(10);
        sliderTable.add(musicSlider).width(250);
        table.add(sliderTable).spaceBottom(80);
        table.row();
        float buttonWidth = 200;
        table.add(backButton).width(buttonWidth);


        //table.debug();//used to show grid lines


        stage.addActor(table);
    }

    public void createButtons(){

        //Regular button
        backButton = new TextButton("Back", textButtonStyle);
        backButton.pad(20);

        controlsButton = new TextButton("Configure Controls", textButtonStyle);
        controlsButton.pad(20);

        //Checkbox
        TextureAtlas checkBoxAtlas = new TextureAtlas("button/checkBox/checkBox.pack");
        Skin checkBoxSkin = new Skin(checkBoxAtlas);

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        checkBoxStyle.checkboxOff = checkBoxSkin.getDrawable("checkBoxUnChecked");
        checkBoxStyle.checkboxOn = checkBoxSkin.getDrawable("checkBoxChecked");
        checkBoxStyle.font = font;
        checkBoxStyle.fontColor = Color.BLACK;

        fullscreenCheckBox = new CheckBox(" Fullscreen",checkBoxStyle);

        //Slider
        TextureAtlas sliderAtlas = new TextureAtlas("button/slider/slider.pack");
        Skin sliderSkin = new Skin(sliderAtlas);
        Slider.SliderStyle sliderStyle = new Slider.SliderStyle();
        sliderStyle.knob = sliderSkin.getDrawable("sliderUp");
        sliderStyle.background = sliderSkin.getDrawable("sliderBackground");

        Label.LabelStyle sliderLabelStyle = new Label.LabelStyle(font, Color.BLACK);

        //soundVolume slider
        soundSlider = new Slider(0,100,10,false,sliderStyle);

        soundVolumeLabel = new Label("Sound Volume:", sliderLabelStyle);
        soundVolumeLabel.setFontScale(1);

        //musicVolume Slider
        musicSlider = new Slider(0,100,10,false,sliderStyle);

        musicVolumeLabel = new Label("Music Volume:", sliderLabelStyle);
        musicVolumeLabel.setFontScale(1);

        /*
         * Checks GeneralOptions for presets
         * Write on one line
         */
        fullscreenCheckBox.setChecked(generalOptions.getOption("isFullscreen") == 1);
        soundSlider.setValue(generalOptions.getOption("soundVolume"));
        musicSlider.setValue(generalOptions.getOption("musicVolume"));


        /*
         * add listener to buttons
         */
        fullscreenCheckBox.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                if(fullscreenCheckBox.isChecked()){
                    Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
                            Gdx.graphics.getDesktopDisplayMode().height, true);
                    generalOptions.setOption("isFullscreen", 1);
                }else{
                    Gdx.graphics.setDisplayMode(720, 480, false);
                    generalOptions.setOption("isFullscreen",0);
                }

            }
        });
        soundSlider.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               generalOptions.setOption("soundVolume", (int)soundSlider.getValue());
           }
        });
        musicSlider.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                generalOptions.setOption("musicVolume", (int)musicSlider.getValue());
            }
        });

        controlsButton.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               generalOptions.saveOptions();
               notifyObserver("configureControls");
           }
        });

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                generalOptions.saveOptions();
                notifyObserver("menu");
            }
        });

    }
}
