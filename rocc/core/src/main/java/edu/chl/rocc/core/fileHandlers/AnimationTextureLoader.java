package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import edu.chl.rocc.core.view.screens.AnimationHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Joel on 2015-05-28.
 */
public class AnimationTextureLoader {

    private HashMap<String,HashMap<String,AnimationHandler>> charactersAnimationHashMap;

    public AnimationTextureLoader(){
        charactersAnimationHashMap = new HashMap<String, HashMap<String, AnimationHandler>>();
        ArrayList<String> animationNames = new ArrayList<String>();
        try{
            FileHandle handle = Gdx.files.internal("animations.txt");
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
                System.out.println(file.name());
                charactersAnimationHashMap.put(file.name(), animationHashMap);
            }
        }
    }

    public HashMap<String,HashMap<String,AnimationHandler>> getHashMap(){
        return charactersAnimationHashMap;
    }
}
