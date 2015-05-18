package edu.chl.rocc.core.utility;

import edu.chl.rocc.core.m2phyInterfaces.IMortal;

/**
 * Created by Joel on 2015-05-18.
 */
public class DeathEvent implements IDeathEvent{

    private final IMortal source;
    private final String message;

    public DeathEvent(IMortal source, final String message){
        this.source = source;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public IMortal getSource() {
        return source;
    }
}
