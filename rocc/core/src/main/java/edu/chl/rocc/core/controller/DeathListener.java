package edu.chl.rocc.core.controller;

import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.utility.IDeathEvent;

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

    }
}
