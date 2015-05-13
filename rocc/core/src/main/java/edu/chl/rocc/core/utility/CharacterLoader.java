package edu.chl.rocc.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 2015-05-13.
 */
public class CharacterLoader {

    private Map<String, Integer> charecaristics;
    public CharacterLoader(String name) {
        if (!Gdx.files.internal("characterDefinition/" + name).exists()) {
            setToDeafault();
            // If it exist get the settings from it
        } else {
            try {
                FileHandle handle = Gdx.files.internal("characterDefinition/" + name);
                BufferedReader br = handle.reader(2);

                String key;
                String value;
                while ((key = br.readLine()) != null && (value = br.readLine()) != null) {
                    // File ends with a .
                    if (!".".equals(key)) {
                        charecaristics.put(key, Integer.parseInt(value));
                    } else {
                        return;
                    }
                }
            } catch (IOException IOEx) {
                setToDeafault();
            } catch (GdxRuntimeException gdxEx) {
                setToDeafault();
            }
        }
    }

    /**
     * Get the value for responding charecaristic
     * @param charecaristic charecaristic
     * @return set value for the action
     */
    public int getCharecaristic(String charecaristic){
        return charecaristics.get(charecaristic);
    }

    private void setToDeafault(){
        charecaristics.put("MaxHP", 100);
        charecaristics.put("Speed", 200);
        charecaristics.put("NumberOfJumps", 1);
        charecaristics.put("JumpForce", 250);
        charecaristics.put("AirForce", 100);
    }
}
