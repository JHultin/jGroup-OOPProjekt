package edu.chl.rocc.core.controller;

import org.jbox2d.dynamics.Fixture;

/**
 * Created by Yen on 2015-05-20.
 */
public class PhyContact implements IContact {

    private PhyContact contact;

    @Override
    public Fixture getFixtureA() {
        return  ;
    }

    @Override
    public Fixture getFixtureB() {
        return null;
    }
}
