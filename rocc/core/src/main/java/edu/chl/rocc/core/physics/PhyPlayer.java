package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.IPlayer;
import edu.chl.rocc.core.model.Player;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyPlayer implements IPlayer {

    private Player player;

    @Override
    public void jump() {
        this.player.jump();
    }

    @Override
    public void move(Direction dir) {
        this.move(dir);
    }

    @Override
    public float getCharacterXPos() {
        return this.player.getCharacterXPos();
    }

    @Override
    public float getCharacterYPos() {
        return this.player.getCharacterYPos();
    }
}
