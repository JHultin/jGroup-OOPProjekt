package edu.chl.rocc.core.fileHandlers;

import java.io.File;

/**
 * A Class which reads the files which contains the different
 * animation texture paths. Credit to Joel.
 * Created by Jacob on 2015-05-19.
 */
public class CharacterTextureLoader extends AbstractTextFileHandler {

        public CharacterTextureLoader(String name){
            super("characterAnimation/" + name + ".txt", new File("characterAnimation"));
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
            super.setInfo("falseRIGHTfalse", "characters/soldier/moveRight.png");
            super.setInfo("falseLEFTfalse", "characters/soldier/moveLeft.png");
            super.setInfo("falseNONERIGHTfalse", "characters/soldier/idleRight.png");
            super.setInfo("falseNONELEFTfalse", "characters/soldier/idleLeft.png");
            super.setInfo("trueRIGHTfalse", "characters/soldier/jumpRight.png");
            super.setInfo("trueLEFTfalse", "characters/soldier/jumpLeft.png");
            super.setInfo("trueNONERIGHTfalse", "characters/soldier/jumpRight.png");
            super.setInfo("trueNONELEFTfalse", "characters/soldier/jumpLeft.png");

            super.setInfo("falseRIGHTtrue", "characters/soldier/moveRightDamage.png");
            super.setInfo("falseLEFTtrue", "characters/soldier/moveLeftDamage.png");
            super.setInfo("falseNONERIGHTtrue", "characters/soldier/idleRightDamage.png");
            super.setInfo("falseNONELEFTtrue", "characters/soldier/idleLeftDamage.png");
            super.setInfo("trueRIGHTtrue", "characters/soldier/jumpRightDamage.png");
            super.setInfo("trueLEFTtrue", "characters/soldier/jumpLeftDamage.png");
            super.setInfo("trueNONERIGHTtrue", "characters/soldier/jumpRightDamage.png");
            super.setInfo("trueNONELEFTtrue", "characters/soldier/jumpLeftDamage.png");
        }
}
