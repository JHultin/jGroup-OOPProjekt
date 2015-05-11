package edu.chl.rocc.core.view.observers;

/**
 * This interface handles adding, deleting and updating
 * all observers.
 * Created by Jacob on 2015-05-06.
 */
public interface IViewObservable {

    public void register(IViewObserver o);
    public void unregister(IViewObserver o);
    public void notifyObserver(String screen);
}
