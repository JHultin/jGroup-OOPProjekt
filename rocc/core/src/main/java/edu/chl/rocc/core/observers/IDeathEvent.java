package edu.chl.rocc.core.observers;

/**
 * A event describing a death of a mortal object, by telling what died and how.
 * Created by Joel on 2015-05-18.
 */
public interface IDeathEvent {

    /**
     * @return the message describing the death
     */
    public String getMessage();

    /**
     * @return the source of the deathEvent
     */
    public IMortal getSource();

    /**
     * @return the killer
     */
    public Object getKiller();
}
