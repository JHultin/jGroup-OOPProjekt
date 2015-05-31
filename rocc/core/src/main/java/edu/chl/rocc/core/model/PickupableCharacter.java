package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IPickupableCharacter;

/**
 * Class for pickupable characters.
 * <br>Implements IPickupableCharacter.
 *
 * Created by Joel on 2015-05-12.
 */
public class PickupableCharacter implements IPickupableCharacter {

    private final String name;

    public PickupableCharacter(String name){
        this.name = name;
    }
    @Override
    public float getX() {
        return 0;
    }

    @Override
    public float getY() {
        return 0;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void destroy() {

    }
}
