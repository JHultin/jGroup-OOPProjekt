package edu.chl.rocc.core;

import com.badlogic.gdx.Game;
import edu.chl.rocc.core.controller.RoCCController;


public class RoCCView extends Game {
    private RoCCController controller;

    @Override
	public void create () {
        controller = new RoCCController(this);
        controller.start();
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
        if (controller != null)
            this.controller.dispose();
	}


    public void setScreen(com.badlogic.gdx.Screen screen){
        super.setScreen(screen);
    }


}
