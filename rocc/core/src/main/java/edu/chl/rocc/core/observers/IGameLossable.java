package edu.chl.rocc.core.observers;

/**
 * Listenable class, notifies listeners if a game is lost
 * Created by Joel on 2015-05-28.
 */
public interface IGameLossable {

    /**
     * @param listener IGameLossListener to add
     */
    public void addLoseListener(IGameLossListener listener);

    /**
     * @param listener IGameLossListener to remove
     */
    public void removeLoseListener(IGameLossListener listener);

    /**
     * Trigger this when the game is lost
     */
    public void gameLost();
}
