package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 2015-05-12.
 */
public class GeneralOptions {

    // The only instance
    private static final GeneralOptions instance = new GeneralOptions();

    // Map of which virtual keys responds to which "command"
    private Map<String, Integer> options;

    // Path to file where settings are saved
    private String filePath;

    /**
     * Getter for the shared instance
     * @return the instance
     */
    public static GeneralOptions getInstance(){
        return instance;
    }

    private GeneralOptions() {
        this.filePath = "options/general.txt";

        // Create the keyMap as a Hashmap
        this.options = new HashMap<String, Integer>(10);

        // If the file specifying the keysettings doesm't exist, set them to default values and write a new file
        if (!Gdx.files.internal(filePath).exists()){
            options.put("soundVolume",  100);
            options.put("musicVolume",  100);
            options.put("isFullscreen", 0);
            saveOptions();

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
                        setOption(key, Integer.parseInt(value));
                    } else {
                        return;
                    }
                }
            } catch (IOException IOEx) {
                saveOptions();
            } catch (GdxRuntimeException gdxEx) {
                saveOptions();
            }
        }
    }

    /**
     * Call to write the current setting to file
     * @return successfully wrote to file
     */
    public boolean saveOptions(){
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
            for (Map.Entry<String, Integer> optionEntry : options.entrySet()){
                pw.println(optionEntry.getKey());
                pw.println(optionEntry.getValue());
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
     * @param option action
     * @return set value for the action
     */
    public int getOption(String option){
        return options.get(option);
    }

    /**
     * Set a value to a action
     * @param option action to set
     * @param value value to give it
     */
    public void setOption(String option, int value) {
        if (options.containsKey(option)){
            options.replace(option, value);
        } else {
            options.put(option, value);
        }
    }
}
