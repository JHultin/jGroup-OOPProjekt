
package edu.chl.rocc.core.controller;
import org.jbox2d.dynamics.Fixture;

/**
 * Created by Yen on 2015-05-20.
 */
public interface IContact {

    public Fixture getFixtureA();

    public Fixture getFixtureB();

}
