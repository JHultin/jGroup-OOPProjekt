package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 2015-05-12.
 */
public class KeyOptions {

    private static final KeyOptions instance = new KeyOptions();

    private Map<String, Integer> keys;

    public static KeyOptions getInstance(){
        return instance;
    }

    private KeyOptions() {
        this.keys = new HashMap<String, Integer>(10);
        if (!Gdx.files.internal("options/keys.txt").exists()){
            keys.put("left",  Input.Keys.LEFT);
            keys.put("right", Input.Keys.RIGHT);
            keys.put("jump",  Input.Keys.SPACE);
        }
        try {
            FileHandle handle = Gdx.files.internal("options/keys.txt");
            BufferedReader br = handle.reader(2);

            String key;
            String value;
            while ((key = br.readLine()) != null && (value = br.readLine()) != null) {
                if (!".".equals(key)){
                    setKey(key, Integer.parseInt(value));
                } else {
                    return;
                }
            }
        } catch (IOException IOEx){
            saveKeys();
        } catch (GdxRuntimeException gdxEx){
            saveKeys();
        }
    }

    public boolean saveKeys(){
        try {
            FileWriter fw = new FileWriter(Gdx.files.internal("options/keys.txt").toString());
            PrintWriter pw = new PrintWriter(fw);

            for (Map.Entry<String, Integer> keyEntry : keys.entrySet()){
                pw.println(keyEntry.getKey());
                pw.println(keyEntry.getValue());
            }

            pw.print(".");
            pw.close();

            return true;
        } catch ( IOException IOEx){
            return false;
        }
    }

    public int getKey(String key){
        return keys.get(key);
    }

    public void setKey(String key, int value) {
        if (keys.containsKey(key)){
            keys.replace(key, value);
        } else {
            keys.put(key, value);
        }
    }
}
