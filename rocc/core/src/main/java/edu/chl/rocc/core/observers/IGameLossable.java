package edu.chl.rocc.core.observers;

/**
 * Created by Joel on 2015-05-28.
 */
public interface IGameLossable {

    public void addLoseListener(IGameLossListener listener);

    public void removeLoseListener(IGameLossListener listener);

    public void gameLost();
}
