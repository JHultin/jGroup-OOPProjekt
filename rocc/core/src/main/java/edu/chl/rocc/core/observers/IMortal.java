package edu.chl.rocc.core.observers;

/**
 * Interface for mortal objects, things in the game that have a death-trigger that should be listened to.
 *
 * Created by Joel on 2015-05-18.
 */
public interface IMortal {

    /**
     * Add a listener to inform when the object dies.
     * @param listener the death listener to add
     */
    public void addDeathListener(IDeathListener listener);

    /**
     * Stop telling the listener when the object dies.
     * @param listener listener to remove
     */
    public void removeDeathListener(IDeathListener listener);

    /**
     * Called when the object dies.
     * @param message a message explaining to the player how the puny mortal died
     */
    public void death(String message);

    /**
     * Called when the object dies.
     * @param deathEvent an event describing what and how that object died
     */
    public void death(IDeathEvent deathEvent);
}
