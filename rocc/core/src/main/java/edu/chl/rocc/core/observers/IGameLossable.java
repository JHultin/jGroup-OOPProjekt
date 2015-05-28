package edu.chl.rocc.core.observers;

/**
 * Created by Joel on 2015-05-28.
 */
public interface IGameLossable {

    public void addListener(IGameLossListener listener);

    public void removeListener(IGameLossListener listener);

    public void gameLost();
}
