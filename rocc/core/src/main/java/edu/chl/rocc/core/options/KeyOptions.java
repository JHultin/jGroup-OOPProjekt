package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * A class handling choosing and storing keysettings.
 * Get hold of the instance by getInstance(), no public constructor.
 *
 * Created by Joel on 2015-05-12.
 */
public class KeyOptions {

    // The only instance
    private static final KeyOptions instance = new KeyOptions();

    // Map of which virtual keys responds to which "command"
    private Map<String, Integer> keys;

    // Path to file where settings are saved
    private String filePath;

    /**
     * Getter for the shared instance
     * @return the instance
     */
    public static KeyOptions getInstance(){
        return instance;
    }

    private KeyOptions() {
        this.filePath = "options/keys.txt";

        // Create the keyMap as a Hashmap
        this.keys = new HashMap<String, Integer>(10);

        // If the file specifying the keysettings doesm't exist, set them to default values and write a new file
        if (!Gdx.files.internal(filePath).exists()){
            keys.put("Move Left",  Input.Keys.A);
            keys.put("Move Right", Input.Keys.D);
            keys.put("Jump",  Input.Keys.SPACE);
            saveKeys();

        // If it exist get the settings from it
        } else {
            try {
                FileHandle handle = Gdx.files.internal(filePath);
                BufferedReader br = handle.reader(2);

                // Every other row gives the key, and the other it's corresponding value
                String key;
                String value;
                // Set the keys as long as there are rows left
                while ((key = br.readLine()) != null && (value = br.readLine()) != null) {
                    // File ends with a .
                    if (!".".equals(key)) {
                        setKey(key, Integer.parseInt(value));
                    } else {
                        return;
                    }
                }
            } catch (IOException IOEx) {
                saveKeys();
            } catch (GdxRuntimeException gdxEx) {
                saveKeys();
            }
        }
    }

    /**
     * Call to write the current setting to file
     * @return successfully wrote to file
     */
    public boolean saveKeys(){
        File dir = new File("options");
        if (!dir.exists()){
            try{
                dir.mkdir();
            } catch (SecurityException se){
                return false;
            }
        }

        // Try to write the current settings to file
        try {
            FileWriter fw = new FileWriter(Gdx.files.internal(filePath).toString());
            PrintWriter pw = new PrintWriter(fw);

            // Write the key followed by responding value
            for (Map.Entry<String, Integer> keyEntry : keys.entrySet()){
                pw.println(keyEntry.getKey());
                pw.println(keyEntry.getValue());
            }

            // End with a .
            pw.print(".");
            pw.close();

            // If successful return true
            return true;
        } catch ( IOException IOEx){
            // If writing failed return false
            return false;
        }
    }

    /**
     * Get the value for responding action
     * @param key action
     * @return set value for the action
     */
    public int getKey(String key){
        return keys.get(key);
    }

    /**
     * Set a value to a action
     * @param key action to set
     * @param value value to give it
     */
    public void setKey(String key, int value) {
        if (keys.containsKey(key)){
            keys.replace(key, value);
        } else {
            keys.put(key, value);
        }
    }
}
