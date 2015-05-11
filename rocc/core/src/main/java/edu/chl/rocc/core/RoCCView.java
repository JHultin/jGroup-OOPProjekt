package edu.chl.rocc.core;

import com.badlogic.gdx.Game;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.controller.RoCCController;
import edu.chl.rocc.core.physics.PhyRoCCModel;
import edu.chl.rocc.core.view.GameViewManager;


public class RoCCView extends Game {
    private IRoCCModel model;
    private RoCCController controller;

    private GameViewManager gameViewManager;

    @Override
	public void create () {



        model = new PhyRoCCModel();

        gameViewManager = new GameViewManager(model);

        controller = new RoCCController(model,this,gameViewManager.getViewObserver());



        //gameViewManager.setActiveView("PLAY");
        gameViewManager.setActiveView("MENU");
        //Sets the current Screen
        setScreen(gameViewManager.getActiveView());
    }

	@Override
	public void resize (int width, int height) {
        super.resize(width,height);
	}

	@Override
	public void render () {
		super.render();
    }

	@Override
	public void pause () {
        super.pause();
	}

	@Override
	public void resume () {
        super.resume();
    }

	@Override
	public void dispose () {
        super.dispose();
	}


    public void setScreen(String screen){
        gameViewManager.setActiveView(screen);
        setScreen(gameViewManager.getActiveView());
    }


}
