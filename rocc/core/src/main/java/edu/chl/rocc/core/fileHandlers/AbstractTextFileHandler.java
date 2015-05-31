package edu.chl.rocc.core.fileHandlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * An abstract class handling writing and reading to and from textfiles
 * Created by Joel on 2015-05-24.
 */
public abstract class AbstractTextFileHandler {

    private final File folder;

    // Map of which values responds to which "command"
    private final Map<String, String> info;

    // Path to file where settings are saved
    private final String filePath;


    /**
     * Constructor describing the file to read and the folder it's placed in
     * @param filePath path for file to read or write to
     * @param folder the directory the file is placed in
     */
    protected AbstractTextFileHandler(String filePath, File folder) {
        this.folder = folder;
        this.filePath = filePath;

        // Create a Hashmap to put the information from the textfile in
        this.info = new HashMap<String, String>();

        // If the file doesn't exist, set values to default and save then create the file.
        if (!Gdx.files.internal(filePath).exists()){
            setToDefault();
            saveInfo();
        } else {
            // If it exist get the settings from it
            try {
                // Create a reader for the file
                FileHandle handle = Gdx.files.internal(filePath);
                BufferedReader br = handle.reader(2);

                // Every other row gives the type of value e.g. what option or property
                // and the other it's corresponding value
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
                // In case of exceptions, set values to default
            } catch (IOException IOEx) {
                setToDefault();
            } catch (GdxRuntimeException gdxEx) {
                setToDefault();
            }
        }
    }

    /**
     * Sets all values to default values.
     * Implement this using the setInfo method
     */
    protected abstract void setToDefault();

    /**
     * Call to write the current setting to file.
     * @return true if writing to file was successful.
     */
    protected boolean saveInfo(){
        // If the directory doesn't exist
        File dir = folder;
        if (!dir.exists()){
            try{
                dir.mkdir();
            } catch (SecurityException se){
                // If it fails return false
                return false;
            }
        }

        // Try to write the current information to file
        try {
            OutputStream output = new FileOutputStream(Gdx.files.internal(filePath).toString());
            Writer writer = new OutputStreamWriter(output);
            PrintWriter pw = new PrintWriter(writer);

            // Write the type of information followed by responding value
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
     * Get the value for responding type of information
     * @param key type of information
     * @return set value for the action
     */
    protected String getInfo(String key){
        return info.get(key);
    }

    /**
     * Set a value to a action
     * @param key action to set
     * @param value value to give it
     */
    protected void setInfo(String key, String value) {
        if (info.containsKey(key)){
            info.replace(key, value);
        } else {
            info.put(key, value);
        }
    }

}
