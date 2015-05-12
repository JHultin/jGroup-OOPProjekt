package edu.chl.rocc.core.m2phyInterfaces;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.controller.CollisionListener;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.Level;
import org.jbox2d.dynamics.Body;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel{

    public void aim(int x, int y);

    public void constructWorld(TiledMap tMap);

    /**
    * Move the character in a given direction.
    */
    public void moveSideways(Direction dir);

    /**
    * Move the followers in a given direction.
    */
    public void moveFollowers(Direction dir);

    /**
    * Make the character jump by changing its y-coordinate.
    */
    public void jump();

    public void shoot(float x, float y);

    /**
    * @return x-coordinate of the given character.
    */
    public float getCharacterXPos(int i);

    /**
    * @return y-coordinate of the given character.
    */
    public float getCharacterYPos(int i);

    /**
    * @return the level.
    */
    public ILevel getLevel();

    /**
    * @return the player.
    */
    public IPlayer getPlayer();

    public void updateWorld(float dt);

    public List<IPickupable> getPickupables();

    public void addFood (IFood food);

    public void removeBodies(List<Body> bodiesToRemove);

    /**
    * @return list of all current existing projectiles in the level.
    */
    public List<IBullet> getBullets();

    /**
    * Creates a bullet/projectile.
    * Add it to list of current existing projectiles.
    */
    //public void createBullet();

    /**
    * @return list of all the playable characters.
    */
    public List<ICharacter> getCharacters();

    public List<IEnemy> getEnemies();

    public void addEnemy(IEnemy enemy);

    public void setCollisionListener(CollisionListener collisionListener);

    public void addCharacter(String name);

    public void dispose();
}
