
package edu.chl.rocc.core.controller;
import edu.chl.rocc.core.m2phyInterfaces.IFixture;

/**
 * Created by Yen on 2015-05-20.
 */
public interface IContact {

    /**
     * @return the first fixture
     */
    public IFixture getFixtureA();

    /**
     * @return the second fixture
     */
    public IFixture getFixtureB();

}
