package edu.chl.rocc.core.observers;

/**
 * Interface for listening to a mortal
 * Created by Joel on 2015-05-18.
 */
public interface IDeathListener {

    /**
     * Triggered when a mortal listened to dies
     * @param death eventObject describing the death of the mortal
     */
    public void deathTriggered(IDeathEvent death);

}
