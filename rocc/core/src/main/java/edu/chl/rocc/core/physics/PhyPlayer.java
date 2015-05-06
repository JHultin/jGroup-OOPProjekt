package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.PhyCharacterFactory;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.Player;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyPlayer implements IPlayer {

    private IPlayer player;

    public PhyPlayer(World world){
        this.player = new Player(new PhyCharacterFactory(world), world);
    }

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
