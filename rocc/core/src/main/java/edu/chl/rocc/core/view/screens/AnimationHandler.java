package edu.chl.rocc.core.view.screens;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * A class that handles the animation.
 * Created by Jacob on 2015-05-11.
 */
public class AnimationHandler {

    private final TextureRegion[] frames;
    private float timeCheck;

    private float delay;
    private int currentFrame;
    private int timesPlayed;

    public AnimationHandler(TextureRegion[] frames, float delay){
        this.frames = frames;
        this.delay = delay;
        currentFrame = 0;
        timesPlayed = 0;
    }

    public void update(){
        if (timeCheck < 1) {
            timeCheck+=delay;
        } else if (timeCheck >= 1) {
            timeCheck = 0;
            step();
        }
    }

    private void step(){
        currentFrame++;
        if(currentFrame == frames.length){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    public TextureRegion getFrame(){
        return  frames[currentFrame];
    }

    public int getTimesPlayed(){
        return timesPlayed;
    }

}
