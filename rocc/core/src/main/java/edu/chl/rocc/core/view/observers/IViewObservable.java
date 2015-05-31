package edu.chl.rocc.core.view.observers;

/**
 * This interface handles adding, deleting and updating
 * all observers.
 * Is implemented by all screens to notify of changes.
 * Created by Jacob on 2015-05-06.
 */
public interface IViewObservable {

    /**
     * method to register observer
     * @param view
     */
    public void register(IViewObserver view);

    /**
     * method to unregister observer
     * @param view
     */
    public void unregister(IViewObserver view);

    /**
     * method to notify observers of change
     * @param screen
     */
    public void notifyObserver(String screen);
}
