package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;

/**
 * Class for projectiles.
 *
 * @author Jenny Orell
 */
public class Bullet implements IBullet {

    private final String name;

    public Bullet(String name){
        this.name = name;
    }
}
