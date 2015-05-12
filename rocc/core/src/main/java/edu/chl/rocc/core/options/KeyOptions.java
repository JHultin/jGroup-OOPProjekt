package edu.chl.rocc.core.options;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

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
