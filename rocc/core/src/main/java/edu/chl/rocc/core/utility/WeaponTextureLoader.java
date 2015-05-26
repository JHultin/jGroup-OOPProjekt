package edu.chl.rocc.core.utility;

import java.io.File;

/**
 * Created by Jacob on 2015-05-21.
 */
public class WeaponTextureLoader extends AbsInformationHandler{
    public WeaponTextureLoader(String name){
        super("weaponTexture/" + name + ".txt", new File("weaponTexture"));
    }


    /**
     * Get the value for requested character animation
     * @param characterAnimation
     * @return set value for the action
     */
    public String getCharacterTexture(String characterAnimation){
        return super.getInfo(characterAnimation);
    }

    protected void setToDefault(){
        super.setInfo("LEFT", "weapons/ak-47/weaponLeft.png");
        super.setInfo("RIGHT", "weapons/ak-47/weaponRight.png");
        super.setInfo("bullet", "weapons/ak-47/bullet.png");

    }
}

