package edu.chl.rocc.core.fileHandlers;

import java.io.File;

/**
 * Class used for getting the correct texture for pickupable objects
 * Created by Jacob on 2015-05-28.
 */
public class PickupableTextureLoader extends AbstractTextFileHandler{

    public PickupableTextureLoader(String name){
        super("pickupableTextures/" + name + ".txt", new File("pickupableTextures"));
    }

    /**
     * Get the value for requested character animation
     * @param characterAnimation animation searched for
     * @return path for responding texture
     */
    public String getPickupableTexture(String characterAnimation){
        return super.getInfo(characterAnimation);
    }

    /**
     * {@inheritDoc}
     */
    protected void setToDefault(){

    }
}
