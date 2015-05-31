package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;

import java.util.ArrayList;


/**
 * The level chooser view.
 * Created by Jacob on 2015-05-30.
 */
public class LevelChooserView extends AbstractMenuView {
    private final Label titleLabel;

    private TextButton backButton;
    private TextButton lvl1Button, lvl2Button, lvl3Button;

    public LevelChooserView(IRoCCModel model){
        super(model);

        titleLabel = new Label("Choose Level", textStyle);
        titleLabel.setFontScale(2);

        createButtons();

        //Adds to table
        //adds title
        table.add(titleLabel).padBottom(70);
        table.row();

        Table lvlButtonTable = new Table();

        float buttonWidth = 100;

        lvlButtonTable.add(lvl1Button).width(buttonWidth).height(50).pad(20);
        lvlButtonTable.add(lvl2Button).width(buttonWidth).height(50).pad(20);
        lvlButtonTable.add(lvl3Button).width(buttonWidth).height(50).pad(20);
        table.add(lvlButtonTable).padBottom(100);
        table.row();

        table.add(backButton).width(150);

        stage.addActor(table);
    }

    @Override
    public void show() {
        super.show();
    }


    public void createButtons(){
        backButton = new TextButton("Back", textButtonStyle);

        lvl1Button = new TextButton("Level 1", textButtonStyle);
        lvl2Button = new TextButton("Level 2", textButtonStyle);
        lvl3Button = new TextButton("Level 3", textButtonStyle);

        //Padding to button
        backButton.pad(20);

        backButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("menu");
            }
        });

        lvl1Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("level1");
            }
        });

        lvl2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("level2");
            }
        });

        lvl2Button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event,float x, float y){
                notifyObserver("level3");
            }
        });
    }
}
