package edu.chl.rocc.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jacob on 2015-05-21.
 */
public class WeaponTextureLoader {

    private Map<String, String> weaponTexture;


    public WeaponTextureLoader(String name){

        weaponTexture = new HashMap<String, String>();

        String filePath = "weaponTexture/" + name + ".txt";


        if (!Gdx.files.internal(filePath).exists()) {
            setToDefaultAnimation();
            // Else if file exist get the settings from it
        } else {
            try {
                FileHandle handle = Gdx.files.internal(filePath);
                BufferedReader br = handle.reader(2);

                String key;
                String value;
                while ((key = br.readLine()) != null && (value = br.readLine()) != null) {
                    // File ends with a .
                    if (!".".equals(key)) {
                        weaponTexture.put(key, value);
                    } else {
                        return;
                    }
                }
            } catch (IOException IOEx) {
                setToDefaultAnimation();
            } catch (GdxRuntimeException gdxEx) {
                setToDefaultAnimation();
            }
        }

    }


    /**
     * Get the value for requested character animation
     * @param characterAnimation
     * @return set value for the action
     */
    public String getCharacterTexture(String characterAnimation){
        return weaponTexture.get(characterAnimation);
    }

    private void setToDefaultAnimation(){
        weaponTexture.put("LEFT", "weapons/ak-47/weaponLeft.png");
        weaponTexture.put("RIGHT", "weapons/ak-47/weaponRight.png");
        weaponTexture.put("bullet", "weapons/ak-47/bullet.png");

    }
}

