package edu.chl.rocc.core.fileHandlers;

import java.io.File;

/**
 * Created by Jacob on 2015-05-28.
 */
public class PickupableTextureLoader extends AbstractTextFileHandler{
    public PickupableTextureLoader(String name){
        super("pickupableTextures/" + name + ".txt", new File("pickupableTextures"));
    }


    /**
     * Get the value for requested character animation
     * @param characterAnimation
     * @return set value for the action
     */
    public String getPickupableTexture(String characterAnimation){
        return super.getInfo(characterAnimation);
    }

    protected void setToDefault(){

    }
}
