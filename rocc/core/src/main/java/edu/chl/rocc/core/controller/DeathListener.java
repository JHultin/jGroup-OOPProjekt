package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.observers.IDeathEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 2015-05-18.
 */
public class DeathListener implements IDeathListener {

    private final List<IDeathEvent> deathsToHandle;

    public DeathListener(){
        this.deathsToHandle = new ArrayList<IDeathEvent>();
    }

    @Override
    public void deathTriggered(IDeathEvent death) {
        deathsToHandle.add(death);
    }

    public List<IDeathEvent> getDeathsToHandle(){
        List<IDeathEvent> listToReturn = new ArrayList<IDeathEvent>(deathsToHandle.size());
        for (IDeathEvent deathEvent : deathsToHandle){
            listToReturn.add(deathEvent);
        }
        deathsToHandle.clear();
        return listToReturn;
    }
}
