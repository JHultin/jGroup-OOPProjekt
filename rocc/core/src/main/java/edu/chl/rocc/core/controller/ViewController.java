package edu.chl.rocc.core.controller;

import com.badlogic.gdx.Game;

/**
 * ViewController is where the application is started.
 * This class also function as the controller for the views in the application.
 */
public class ViewController extends Game {
    private MainController controller;

    @Override
	public void create () {
        controller = new MainController(this);
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

    @Override
    public void setScreen(com.badlogic.gdx.Screen screen){
        super.setScreen(screen);
    }


}
