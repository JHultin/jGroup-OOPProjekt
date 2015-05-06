package edu.chl.rocc.core.physics;

/**
 * Created by Yen on 2015-05-05.
 */
public enum PhyVariables {
    BITBODY((short) 4),BITGROUND((short)2);

    private short value;

    private PhyVariables(short value){
        this.value = value;
    }

    public short getValue() {
        return value;
    }
};
