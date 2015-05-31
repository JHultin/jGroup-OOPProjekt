package edu.chl.rocc.core.observers;

/**
 * Interface for listeners of games that can lose
 * Created by Joel on 2015-05-28.
 */
public interface IGameLossListener {

    /**
     * Triggered when a game that is listened to is lost
     */
    public void gameLost();
}
