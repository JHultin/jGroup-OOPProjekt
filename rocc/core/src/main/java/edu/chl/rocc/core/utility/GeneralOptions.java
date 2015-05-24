package edu.chl.rocc.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 2015-05-12.
 */
public class GeneralOptions extends AbsInformationHandler {

    // The only instance
    private static final GeneralOptions instance = new GeneralOptions();

    /**
     * Getter for the shared instance
     * @return the instance
     */
    public static GeneralOptions getInstance(){
        return instance;
    }

    private GeneralOptions() {
        super("assets/options/general.txt", new File("assets/options"));
    }

    /**
     * Call to write the current setting to file
     * @return successfully wrote to file
     */
    public boolean saveOptions(){
        return super.saveInfo();
    }

    /**
     * Get the value for responding action
     * @param option action
     * @return set value for the action
     */
    public int getOption(String option){
        return Integer.parseInt(super.getInfo(option));
    }

    /**
     * Set a value to a action
     * @param option action to set
     * @param value value to give it
     */
    public void setOption(String option, int value) {
        super.setInfo(option, Integer.toString(value));
    }

    @Override
    protected void setToDefault() {
        super.setInfo("soundVolume", "100");
        super.setInfo("musicVolume", "100");
        super.setInfo("isFullscreen", "0");
    }
}
