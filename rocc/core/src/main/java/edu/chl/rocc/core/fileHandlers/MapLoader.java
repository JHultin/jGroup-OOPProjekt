package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A class getting the paths for all importable maps.
 * Created by Jacob on 2015-05-29.
 */
public class MapLoader {

    private List<String> mapNames;

    /**
     * Constructor, saves the names of all maps to a List
     */
    public MapLoader(){
        mapNames = new ArrayList<String>();

        try{
            // Reads the textfile containing paths to all maps.
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

    /**
     * @return a list of paths to all maps
     */
    public List<String> getTiledMapList(){
        return mapNames;
    }

}
