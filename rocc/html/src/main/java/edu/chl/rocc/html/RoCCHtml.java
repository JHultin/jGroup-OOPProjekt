package edu.chl.rocc.html;

import edu.chl.rocc.core.controller.ViewController;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class RoCCHtml extends GwtApplication {
	@Override
	public ApplicationListener getApplicationListener () {
		return new ViewController();
	}
	
	@Override
	public GwtApplicationConfiguration getConfig () {
		return new GwtApplicationConfiguration(480, 320);
	}
}
