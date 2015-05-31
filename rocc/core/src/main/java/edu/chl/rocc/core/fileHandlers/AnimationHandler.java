package edu.chl.rocc.core.fileHandlers;

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

    /**
     * Constructor
     * @param frames frames to desplay
     * @param delay how much time should take between each time update is called
     */
    public AnimationHandler(TextureRegion[] frames, float delay){
        this.frames = frames;
        this.delay = delay;
        currentFrame = 0;
        timesPlayed = 0;
    }

    /**
     * Goes one timestep closer to the next update
     */
    public void update(){
        if (timeCheck < 1) {
            timeCheck+=delay;
        } else if (timeCheck >= 1) {
            timeCheck = 0;
            step();
        }
    }

    /**
     * Update the animation, and go to the next frame
     */
    private void step(){
        currentFrame++;
        if(currentFrame == frames.length){
            currentFrame = 0;
            timesPlayed++;
        }
    }

    /**
     * @return the current frame
     */
    public TextureRegion getFrame(){
        return  frames[currentFrame];
    }

    /**
     * @return number of times entire animation has played
     */
    public int getTimesPlayed(){
        return timesPlayed;
    }

}
