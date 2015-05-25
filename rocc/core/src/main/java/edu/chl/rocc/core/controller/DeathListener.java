package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.model.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.observers.IDeathEvent;

/**
 * Created by Joel on 2015-05-18.
 */
public class DeathListener implements IDeathListener {

    private final IRoCCModel model;

    public DeathListener(IRoCCModel model){
        this.model = model;
    }

    @Override
    public void deathTriggered(IDeathEvent death) {
        model.handleDeath(death);
    }
}
