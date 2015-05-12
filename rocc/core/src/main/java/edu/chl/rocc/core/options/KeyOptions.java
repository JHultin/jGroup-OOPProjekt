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

    private int left_key;
    private int right_key;
    private int jump_key;
    private Map<String, Integer> keys;

    public static KeyOptions getInstance(){
        return instance;
    }

    private KeyOptions() {
        this.keys = new HashMap<String, Integer>(10);
        if (!Gdx.files.internal("options/keys.txt").exists()){
            keys.put("left",  Input.Keys.LEFT);
            keys.put("right", Input.Keys.RIGHT);
            keys.put("jump",  Input.Keys.LEFT);
        }
        try {
            FileHandle handle = Gdx.files.internal("options/keys.txt");
            BufferedReader br = handle.reader(2);

            String str;
            String key;
            while ((str = br.readLine()) != null && (key = br.readLine()) != null) {
                //try {
                    if (!".".equals(str)){
                        if (keys.containsKey(str)){
                            keys.replace(str, Integer.parseInt(key));
                        } else {
                            keys.put(str, Integer.parseInt(key));
                        }

                    } else {
                        return;
                    }
                    /*
                    if ("left".equals(str)) {
                        left_key = Integer.parseInt(key);
                    } else if ("right".equals(str)) {
                        right_key = Integer.parseInt(key);
                    } else if ("jump".equals(str)) {
                        jump_key = Integer.parseInt(key);
                    } else if (".".equals(str)) {
                        return;
                    }

                } catch (NumberFormatException nfe) {
                    if (str.equals("left")) {
                        left_key = Input.Keys.LEFT;
                    } else if (str.equals("right")) {
                        right_key = Input.Keys.RIGHT;
                    } else if (str.equals("jump")) {
                        jump_key = Input.Keys.SPACE;
                    }
                }*/
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

            pw.println("left");
            pw.println(left_key);
            pw.println("right");
            pw.println(right_key);
            pw.println("jump");
            pw.println(jump_key);

            pw.print(".");

            pw.close();

            return true;
        } catch ( IOException ioex){
            return false;
        }
    }

    public int getKey(String key){
        return keys.get(key);
    }

    public int getLeftKey() {
        return left_key;
    }

    public void setLeftKey(int left_key) {
        this.left_key = left_key;
    }

    public int getRightKey() {
        return right_key;
    }

    public void setRightKey(int right_key) {
        this.right_key = right_key;
    }

    public int getJumpKey() {
        return jump_key;
    }

    public void setJumpKey(int jump_key) {
        this.jump_key = jump_key;
    }
}
