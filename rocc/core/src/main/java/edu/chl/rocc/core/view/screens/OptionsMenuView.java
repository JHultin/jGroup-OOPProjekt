package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.GdxRuntimeException;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.fileHandlers.GeneralOptions;

/**
 * Created by Jacob on 2015-05-11.
 */
public class OptionsMenuView extends AbstractMenuView{
    //Title
    private final Label titleLabel;

    private TextButton backButton, controlsButton;
    private CheckBox fullscreenCheckBox;
    private Slider soundSlider;
    private Label soundVolumeLabel, musicVolumeLabel;
    private Slider musicSlider;

    private final GeneralOptions generalOptions;

    public OptionsMenuView(IRoCCModel model){
        super(model);

        generalOptions = GeneralOptions.getInstance();

        //Creating Options title
        titleLabel = new Label("Options", textStyle);
        titleLabel.setFontScale(2);

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

        stage.addActor(table);
    }

    //Creates all of the buttons, checkbox and sliders and adds listeners
    private void createButtons(){
        backButton = new TextButton("Back", textButtonStyle);
        backButton.pad(20);

        controlsButton = new TextButton("Configure Controls", textButtonStyle);
        controlsButton.pad(20);

        //Checkbox
        try {//Is used to prevent the game from chrashing if the checkBox files isn't found
            TextureAtlas checkBoxAtlas = new TextureAtlas(Gdx.files.internal("button/checkBox/checkBox.pack"));
            Skin checkBoxSkin = new Skin(checkBoxAtlas);

            CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
            checkBoxStyle.checkboxOff = checkBoxSkin.getDrawable("checkBoxUnChecked");
            checkBoxStyle.checkboxOn = checkBoxSkin.getDrawable("checkBoxChecked");
            checkBoxStyle.font = font;
            checkBoxStyle.fontColor = Color.BLACK;

            fullscreenCheckBox = new CheckBox(" Fullscreen", checkBoxStyle);
        } catch (GdxRuntimeException ex){

        }

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
        if(fullscreenCheckBox != null)
            fullscreenCheckBox.setChecked(generalOptions.getOption("isFullscreen") == 1);
        soundSlider.setValue(generalOptions.getOption("soundVolume"));
        musicSlider.setValue(generalOptions.getOption("musicVolume"));

        //add listener to buttons
        if(fullscreenCheckBox != null) {
            fullscreenCheckBox.addListener(new ClickListener() {
                @Override
                public void clicked(InputEvent event, float x, float y) {
                    if (fullscreenCheckBox.isChecked()) {//sets fullscreen if checked
                        Gdx.graphics.setDisplayMode(Gdx.graphics.getDesktopDisplayMode().width,
                                Gdx.graphics.getDesktopDisplayMode().height, true);
                        generalOptions.setOption("isFullscreen", 1);
                    } else {
                        Gdx.graphics.setDisplayMode(720, 480, false);
                        generalOptions.setOption("isFullscreen", 0);
                    }

                }
            });
        }
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
