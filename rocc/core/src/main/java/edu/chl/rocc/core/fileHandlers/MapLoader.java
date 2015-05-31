package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Jacob on 2015-05-29.
 */
public class MapLoader {

    ArrayList<String> mapNames;

    public MapLoader(){
        mapNames = new ArrayList<String>();

        try{
            FileHandle handle = Gdx.files.internal("mapDefinitions/maps.txt");
            BufferedReader br = handle.reader(2);
            String str;
            while ((str = br.readLine()) != null){
                mapNames.add(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTiledMapList(){
        return mapNames;
    }

}
