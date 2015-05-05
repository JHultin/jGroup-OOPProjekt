package edu.chl.rocc.core.factories;

import edu.chl.rocc.core.m2phyInterfaces.ICharacter;

/**
 * Created by Joel on 2015-05-04.
 */
public interface ICharacterFactory {

    public ICharacter createCharacter(String name);

}
