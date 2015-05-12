package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.GdxRuntimeException;

import java.io.*;

/**
 * Created by Joel on 2015-05-12.
 */
public class KeyOptions {

    private static final KeyOptions instance = new KeyOptions();

    private int left_key;
    private int right_key;
    private int jump_key;

    public static KeyOptions getInstance(){
        return instance;
    }

    private KeyOptions(){
        if (Gdx.files.internal("options/keys.txt").exists()){
            FileHandle handle = Gdx.files.internal("options/keys.txt");

            String str;
            String key;
            while ((str = handle.readString()) != null &&
                    (key = handle.readString()) != null){
                try {
                    if (str.equals("left")) {
                        left_key = Integer.parseInt(key);
                    } else if (str.equals("right")) {
                        right_key = Integer.parseInt(key);
                    } else if (str.equals("jump")) {
                        jump_key = Integer.parseInt(key);
                    } else {
                        return;
                    }
                } catch (NumberFormatException nfe){
                    if (str.equals("left")) {
                        left_key  = Input.Keys.LEFT;
                    } else if (str.equals("right")) {
                        right_key = Input.Keys.RIGHT;
                    } else if (str.equals("jump")) {
                        jump_key  = Input.Keys.SPACE;
                    }
                }
            }
        } else {
            left_key  = Input.Keys.LEFT;
            right_key = Input.Keys.RIGHT;
            jump_key  = Input.Keys.SPACE;
        }
    }

    public boolean saveKeys(){

        if (!Gdx.files.internal("options/keys.txt").exists()){
            try {
                FileWriter fw = new FileWriter(Gdx.files.internal("options/keys.txt").toString());
                PrintWriter pw = new PrintWriter(fw);

                pw.println("text!");
                pw.println("mer text");
                pw.println("ännu mer text");

                pw.close();
            } catch ( IOException ioex){

            }
        }
        
        return false;
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
