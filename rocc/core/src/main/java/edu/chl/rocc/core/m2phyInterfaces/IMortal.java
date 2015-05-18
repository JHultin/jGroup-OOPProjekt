package edu.chl.rocc.core.m2phyInterfaces;

import edu.chl.rocc.core.controller.IDeathListener;

/**
 * Interface for mortal objects, things in the game that have a death-trigger that should be listened to
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
     * @param listener
     */
    public void remveDeathListener(IDeathListener listener);

    /**
     * Called when the object dies.
     */
    public void death();
}
