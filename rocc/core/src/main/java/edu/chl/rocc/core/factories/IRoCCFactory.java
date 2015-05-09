package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IFood;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;

/**
 * Created by Joel on 2015-05-08.
 */
public interface IRoCCFactory {

    public IPlayer createPlayer(String name);

    public ILevel createLevel (String name);

    public ICharacter createCharacter(String name, int x, int y);

    public IFood createFood(String name, int x, int y);

}
