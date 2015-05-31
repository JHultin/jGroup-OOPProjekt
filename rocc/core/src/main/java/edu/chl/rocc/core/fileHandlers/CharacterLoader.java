package edu.chl.rocc.core.fileHandlers;

import java.io.*;

/**
 * Class for loading values for the characteristics of characters or enemies.
 * Created by Joel on 2015-05-13.
 */
public class CharacterLoader extends AbstractTextFileHandler {

    /**
     * Create a loader containing values of characteristics for an Character
     * @param name name of the character whose characteristics you want.
     */
    public CharacterLoader(String name) {
        this(name, false);
    }

    /**
     * Create a loader containing values of characteristics for an Character or Enemy
     * @param name name of the character or enemy whose characteristics you want.
     * @param isEnemy if you which to load an enemy
     */
    public CharacterLoader(String name, boolean isEnemy){
        // Use the AbstractTextFileHandler to handle the files
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

    /**
     * {@inheritDoc}
     */
    protected void setToDefault(){
        super.setInfo("MaxHP"        , "100");
        super.setInfo("Speed"        , "200");
        super.setInfo("NumberOfJumps", "1");
        super.setInfo("JumpForce"    , "250");
        super.setInfo("AirForce"     , "100");
        super.setInfo("Weapon"       , "0");
        super.setInfo("Value"        , "25");
    }
}
