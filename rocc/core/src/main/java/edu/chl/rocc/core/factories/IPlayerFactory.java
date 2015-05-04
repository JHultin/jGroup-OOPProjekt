package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.IPlayer;

/**
 * Created by Joel on 2015-05-04.
 */
public interface IPlayerFactory {

    public IPlayer createPlayer(String name);

}
