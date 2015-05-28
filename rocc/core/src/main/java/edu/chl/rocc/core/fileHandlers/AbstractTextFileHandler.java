package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Joel on 2015-05-24.
 */
public abstract class AbstractTextFileHandler {

    private final File folder;

    // Map of which values responds to which "command"
    private final Map<String, String> info;

    // Path to file where settings are saved
    private final String filePath;


    protected AbstractTextFileHandler(String filePath, File folder) {
        this.folder = folder;
        this.filePath = filePath;

        // Create the keyMap as a Hashmap
        this.info = new HashMap<String, String>();

        // If the file specifying the keysettings doesm't exist, set them to default values and write a new file
        if (!Gdx.files.internal(filePath).exists()){
            setToDefault();
            saveInfo();
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
                        setInfo(key, value);
                    } else {
                        return;
                    }
                }
            } catch (IOException IOEx) {
                saveInfo();
            } catch (GdxRuntimeException gdxEx) {
                saveInfo();
            }
        }
    }

    protected abstract void setToDefault();

    /**
     * Call to write the current setting to file
     * @return successfully wrote to file
     */
    protected boolean saveInfo(){
        File dir = folder;
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
            for (Map.Entry<String, String> optionEntry : info.entrySet()){
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
    protected String getInfo(String option){
        return info.get(option);
    }

    /**
     * Set a value to a action
     * @param option action to set
     * @param value value to give it
     */
    protected void setInfo(String option, String value) {
        if (info.containsKey(option)){
            info.replace(option, value);
        } else {
            info.put(option, value);
        }
    }

}
