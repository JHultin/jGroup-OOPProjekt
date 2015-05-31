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
 * Created by Joel on 2015-05-28.
 */
public class ViewTextureLoader {

    private final HashMap<String, HashMap<String, Texture>> weaponHashMap;
    private final HashMap<String,HashMap<String,AnimationHandler>> charactersAnimationHashMap;

    public ViewTextureLoader(){
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();
        ArrayList<String> animationNames = new ArrayList<String>();
        fillList("textureDefinitions/animations.txt", animationNames);
        ArrayList<String> characterNames = new ArrayList<String>();
        fillList("textureDefinitions/characters.txt", characterNames);

        for (String name : characterNames){
            HashMap<String, AnimationHandler> animationHashMap = new HashMap<String, AnimationHandler>(20);
            for (String animationName : animationNames){
                if (animationName != null) {
                    TextureRegion[] textureRegions;
                    Texture texture = new Texture(Gdx.files.internal("characters/" + name + "/" + animationName + ".png"));
                    if (texture.getWidth() > 50) {//Checks if texture contains several images and needs to split
                        textureRegions = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight())[0];
                    } else {
                        textureRegions = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0];
                    }
                    animationHashMap.put(animationName, new AnimationHandler(textureRegions, 1 / 12f));
                }
            }
            charactersAnimationHashMap.put(name, animationHashMap);
        }

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

    public HashMap<String,HashMap<String,AnimationHandler>> getCharactersAnimationHashMap(){
        return charactersAnimationHashMap;
    }

    public HashMap<String, HashMap<String, Texture>> getWeaponHashMap(){
        return weaponHashMap;
    }
}
