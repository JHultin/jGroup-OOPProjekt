package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
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
        try{
            FileHandle handle = Gdx.files.internal("textureDefinitions/animations.txt");
            BufferedReader br = handle.reader(2);
            String str;
            while ((str = br.readLine()) != null){
                animationNames.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (FileHandle file : Gdx.files.internal("characters/").list()){
            if (file.isDirectory()){
                HashMap<String, AnimationHandler> animationHashMap = new HashMap<String, AnimationHandler>(20);
                for (String name : animationNames){
                    if (name != null) {
                        TextureRegion[] textureRegions;
                        Texture texture = new Texture(file.pathWithoutExtension() + "/" + name + ".png");
                        if (texture.getWidth() > 50) {//Checks if texture contains several images and needs to split
                            textureRegions = TextureRegion.split(texture, texture.getWidth() / 3, texture.getHeight())[0];
                        } else {
                            textureRegions = TextureRegion.split(texture, texture.getWidth(), texture.getHeight())[0];
                        }
                        animationHashMap.put(name, new AnimationHandler(textureRegions, 1 / 12f));
                    }
                }
                charactersAnimationHashMap.put(file.name(), animationHashMap);
            }
        }

        weaponHashMap = new HashMap<String, HashMap<String, Texture>>();
        ArrayList<String> weaponTextures = new ArrayList<String>(); //{"LEFT","RIGHT","bullet"};
        fillList("textureDefinitions/weaponTextures.txt", weaponTextures);

        for (FileHandle file : Gdx.files.internal("weapons/").list()){
            HashMap<String, Texture> textureHashmap = new HashMap<String, Texture>();

            for(String tex : weaponTextures){
                Texture texture = new Texture(Gdx.files.internal(file.pathWithoutExtension() + "/" + tex + ".png"));
                textureHashmap.put(tex, texture);
            }
            weaponHashMap.put(file.name(), textureHashmap);
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
