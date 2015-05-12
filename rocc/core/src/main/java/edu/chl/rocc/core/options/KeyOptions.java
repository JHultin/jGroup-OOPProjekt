package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.files.FileHandle;

import java.io.BufferedReader;
import java.io.IOException;

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
        left_key  = Input.Keys.LEFT;
        right_key = Input.Keys.RIGHT;
        jump_key  = Input.Keys.SPACE;
        try {
            FileHandle handle = Gdx.files.internal("options/keys.txt");
            BufferedReader br = handle.reader(2);

            String str;
            String key;
            while ((str = br.readLine()) != null && (key = br.readLine()) != null){
                try {
                    if (str.equals("left")) {
                        left_key = Integer.parseInt(key);
                    } else if (str.equals("right")) {
                        right_key = Integer.parseInt(key);
                    } else if (str.equals("jump")) {
                        jump_key = Integer.parseInt(key);
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
        } catch (IOException e){

        }
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
