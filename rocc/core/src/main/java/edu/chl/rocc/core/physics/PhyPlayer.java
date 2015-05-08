package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.PhyCharacterFactory;
import edu.chl.rocc.core.factories.PhyRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
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
        this.player = new Player(new PhyRoCCFactory(world));
    }

    @Override
    public void jump() {
        this.player.jump();
    }

    @Override
    public void move(Direction dir) {
        this.player.move(dir);
    }

    @Override
    public float getCharacterXPos(int i) {
        return this.player.getCharacterXPos(i);
    }

    @Override
    public float getCharacterYPos(int i) {
        return this.player.getCharacterYPos(i);
    }

    public void addCharacter(ICharacter c){
        player.addCharacter(c);
    }

}
