package edu.chl.rocc.core.options;

/**
 * Created by Joel on 2015-05-12.
 */
public class KeyOptions {

    private static final KeyOptions instance = new KeyOptions();

    private int left_key, right_key;
    private int jump_key;

    private KeyOptions(){

    }

    public KeyOptions getInstance(){
        return instance;
    }
}
