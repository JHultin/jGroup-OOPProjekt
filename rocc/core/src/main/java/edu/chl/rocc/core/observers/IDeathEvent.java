package edu.chl.rocc.core.observers;

/**
 * A event describing a death of a mortal object, by telling what died and how.
 * Created by Joel on 2015-05-18.
 */
public interface IDeathEvent {

    public String getMessage();

    public IMortal getSource();
}
