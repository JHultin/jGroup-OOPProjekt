package edu.chl.rocc.core.model.physics;

import edu.chl.rocc.core.model.m2phyInterfaces.IFixture;
import org.jbox2d.dynamics.Fixture;


/**
 * Created by Yen on 2015-05-20.
 */
public class PhyFixture implements IFixture {

    //Unecessary class

    private Fixture fixture;

    public PhyFixture(Fixture fixture){
        this.fixture = fixture;
    }

    @Override
    public Object getUserData() {
        return this.fixture.getUserData();
    }

    @Override
    public Object getBodyUserData() {
        return this.fixture.getBody().getUserData();
    }
}
