package edu.chl.rocc.core.physics;

/**
 * Created by Yen on 2015-05-05.
 */
public enum PhyVariables {
    BITBODY(4),BITGROUND(2);

    private int value;

    private PhyVariables(int value){
        this.value = value;
    }

    public int getValue() {
        return value;
    }
};
