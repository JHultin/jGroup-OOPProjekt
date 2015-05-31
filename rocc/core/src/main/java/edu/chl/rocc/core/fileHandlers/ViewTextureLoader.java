package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.loaders.TextureLoader;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Class used to load all textures needed for the PlayView
 * Created by Joel on 2015-05-28.
 */
public class ViewTextureLoader {

    // Saves all textures and animationHandlers in hashMaps
    private final HashMap<String, HashMap<String, Texture>> weaponHashMap;
    private final HashMap<String, HashMap<String, AnimationHandler>> charactersAnimationHashMap;

    /**
     * Constructor, here are all the textures loaded
     */
    public ViewTextureLoader(){
        // Create the hashmap for the animationHandlers
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();

        // Find all characters needed to be drawn and the different textures needed for each character
        ArrayList<String> animationNames = new ArrayList<String>();
        fillList("textureDefinitions/animations.txt", animationNames);
        ArrayList<String> characterNames = new ArrayList<String>();
        fillList("textureDefinitions/characters.txt", characterNames);

        // For each character make a hashmap containing all animationHandlers
        for (String name : characterNames){
            HashMap<String, AnimationHandler> animationHashMap = new HashMap<String, AnimationHandler>(20);

            // Create all necessary animationHandlers, each responding to a moveState from character or enemy
            for (String animationName : animationNames){
                if (animationName != null) {
                    TextureRegion[] textureRegions;
                    Texture texture = new Texture(Gdx.files.internal("characters/" + name + "/" + animationName + ".png"));

                    //Checks if texture contains several images and needs to split
                    if (texture.getWidth() > 50) {
                        textureRegions = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight())[0];
                    } else {
                        textureRegions = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0];
                    }
                    // Put in the hashmap
                    animationHashMap.put(animationName, new AnimationHandler(textureRegions, 1 / 12f));
                }
            }
            charactersAnimationHashMap.put(name, animationHashMap);
        }

        // Do the same for weapons
        weaponHashMap = new HashMap<String, HashMap<String, Texture>>();
        ArrayList<String> weaponTextures = new ArrayList<String>();
        fillList("textureDefinitions/weaponTextures.txt", weaponTextures);
        ArrayList<String> weaponNames = new ArrayList<String>();
        fillList("textureDefinitions/weaponNames.txt", weaponNames);

        for (String weaponName : weaponNames){
            HashMap<String, Texture> textureHashmap = new HashMap<String, Texture>();

            for(String textureType : weaponTextures){
                Texture texture = new Texture(Gdx.files.internal("weapons/" + weaponName + "/" + textureType + ".png"));
                textureHashmap.put(textureType, texture);
            }
            weaponHashMap.put(weaponName, textureHashmap);
        }
    }

    // Fill a given list with strings from a textfile located at path
    private void fillList(String path, ArrayList<String> names){
        try{
            FileHandle handle = Gdx.files.internal(path);
            BufferedReader br = handle.reader(2);
            String str;
            while ((str = br.readLine()) != null){
                names.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @return a HashMap containing a HashMap of all necessary AnimationHandler for each Character
     */
    public HashMap<String,HashMap<String,AnimationHandler>> getCharactersAnimationHashMap(){
        return charactersAnimationHashMap;
    }

    /**
     * @return a HashMap containing a HashMap of all necessary Textures for each Weapon
     */
    public HashMap<String, HashMap<String, Texture>> getWeaponHashMap(){
        return weaponHashMap;
    }
}
