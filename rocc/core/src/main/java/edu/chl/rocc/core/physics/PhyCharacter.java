package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.model.*;
import edu.chl.rocc.core.model.Character;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyCharacter implements ICharacter {

    private Character character;

    @Override
    public int getHP() {
        return this.character.getHP();
    }

    @Override
    public void setHP(int value) {
        this.character.setHP(value);
    }

    @Override
    public void incHP(int value) {
        this.character.incHP(value);
    }

    @Override
    public void decHP(int value) {
        this.character.decHP(value);
    }

    @Override
    public void move(Direction dir) {
        // body.move
    }

    @Override
    public void jump() {
        // body.move
    }

    @Override
    public float getX() {
        // return body.x
        return 0;
    }

    @Override
    public float getY() {
        // return body.y
        return 0;
    }
}
