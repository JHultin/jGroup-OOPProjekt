package edu.chl.rocc.core.view.observers;

/**
 * Created by Jacob on 2015-05-06.
 */
public interface IViewObserver {

/**
 * The observers update method is called
 * when the ViewObservable changes.
 */
 public void viewUpdated(String screen);

}
