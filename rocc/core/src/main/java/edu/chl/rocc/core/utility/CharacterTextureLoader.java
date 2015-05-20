package edu.chl.rocc.core.utility;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A Class which reads the files which contains the different
 * animation texture paths. Credit to Joel.
 * Created by Jacob on 2015-05-19.
 */
public class CharacterTextureLoader {

        private Map<String, String> characterAnimations;


        public CharacterTextureLoader(String name){

            characterAnimations = new HashMap<String, String>();

            String filePath = "characterAnimation/" + name + ".txt";


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
                            characterAnimations.put(key, value);
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
            return characterAnimations.get(characterAnimation);
        }

        private void setToDefaultAnimation(){
            characterAnimations.put("falseRIGHTfalse", "characters/soldier/moveRight.png");
            characterAnimations.put("falseLEFTfalse", "characters/soldier/moveLeft.png");
            characterAnimations.put("falseNONERIGHTfalse", "characters/soldier/idleRight.png");
            characterAnimations.put("falseNONELEFTfalse", "characters/soldier/idleLeft.png");
            characterAnimations.put("trueRIGHTfalse", "characters/soldier/jumpRight.png");
            characterAnimations.put("trueLEFTfalse", "characters/soldier/jumpLeft.png");
            characterAnimations.put("trueNONERIGHTfalse", "characters/soldier/jumpRight.png");
            characterAnimations.put("trueNONELEFTfalse", "characters/soldier/jumpLeft.png");

            characterAnimations.put("falseRIGHTtrue", "characters/soldier/moveRightDamage.png");
            characterAnimations.put("falseLEFTtrue", "characters/soldier/moveLeftDamage.png");
            characterAnimations.put("falseNONERIGHTtrue", "characters/soldier/idleRightDamage.png");
            characterAnimations.put("falseNONELEFTtrue", "characters/soldier/idleLeftDamage.png");
            characterAnimations.put("trueRIGHTtrue", "characters/soldier/jumpRightDamage.png");
            characterAnimations.put("trueLEFTtrue", "characters/soldier/jumpLeftDamage.png");
            characterAnimations.put("trueNONERIGHTtrue", "characters/soldier/jumpRightDamage.png");
            characterAnimations.put("trueNONELEFTtrue", "characters/soldier/jumpLeftDamage.png");
        }
}
