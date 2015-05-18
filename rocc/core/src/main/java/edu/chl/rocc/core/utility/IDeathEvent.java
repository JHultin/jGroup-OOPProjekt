package edu.chl.rocc.core.utility;

import edu.chl.rocc.core.m2phyInterfaces.IMortal;

/**
 * Created by Joel on 2015-05-18.
 */
public interface IDeathEvent {

    public String getMessage();

    public IMortal getSource();
}
