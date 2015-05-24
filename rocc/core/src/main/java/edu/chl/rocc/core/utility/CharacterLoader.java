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
public class CharacterLoader extends AbsInformationHandler {

    public CharacterLoader(String name) {
        this(name, false);
    }

    public CharacterLoader(String name, boolean isEnemy){
        super("" + (isEnemy ? "enemyDefinition/" : "characterDefinition/") + name + ".txt",
                (isEnemy ? new File("enemyDefinition") : new File("characterDefinition")));
    }


    /**
     * Get the value for responding charecaristic
     * @param charecaristic charecaristic
     * @return set value for the action
     */
    public int getCharecaristic(String charecaristic){
        return Integer.parseInt(super.getInfo(charecaristic));
    }

    protected void setToDefault(){
        super.setInfo("MaxHP"        , "100");
        super.setInfo("Speed"        , "200");
        super.setInfo("NumberOfJumps", "1");
        super.setInfo("JumpForce"    , "250");
        super.setInfo("AirForce"     , "100");
    }
}
