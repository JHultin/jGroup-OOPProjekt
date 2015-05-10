package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.factories.PhyRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.Player;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyPlayer implements IPlayer {

    private IPlayer player;
    private World world;

    public PhyPlayer(World world){
        this.player = new Player(new PhyRoCCFactory(world));

        this.world = world;
    }

    @Override
    public void jump() {
        this.player.jump();
    }

    @Override
    public void move(Direction dir) {
        this.player.move(dir);
    }

    public void shoot(float x, float y, Vec2 vec){
        createBullet(x, y, vec);
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

    @Override
    public List<ICharacter> getCharacters() {
        return player.getCharacters();
    }

    /*
    * Creates and fires a bullet.
    * Temporarily placed in PhyPlayer, will later be moved to a Weapon class.
    */
    public void createBullet(float x, float y, Vec2 vec){
        IBullet bullet = new PhyBullet(this.world, x, y, vec);
    }
}
