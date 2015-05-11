package edu.chl.rocc.core.m2phyInterfaces;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.Level;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel{

    public void aim(int x, int y);

    public void constructWorld(TiledMap tMap);

    /*
    * Move the character in a given direction.
    */
    public void moveSideways(Direction dir);

    /*
    * Move the followers in a given direction.
    */
    public void moveFollowers(Direction dir);

    /*
    * Make the character jump by changing its y-coordinate.
    */
    public void jump();

    public void shoot();

    /*
    * Returns the x-coordinate of the given character.
    */
    public float getCharacterXPos(int i);

    /*
    * Returns the y-coordinate of the given character.
    */
    public float getCharacterYPos(int i);

    /*
    * Returns the level.
    */
    public ILevel getLevel();

    /*
    * Returns the player.
    */
    public IPlayer getPlayer();

    public void updateWorld(float dt);

    public List<IFood> getFoods();

    public void addFood (IFood food);

    /*
    * Returns a list of all current existing projectiles in the level.
    */
    public List<IBullet> getBullets();

    /*
    * Creates a bullet/projectile.
    * Add it to list of current existing projectiles.
    */
    public void createBullet();

    /*
    * Returns a list of all the playable characters.
    */
    public List<ICharacter> getCharacters();

    public List<IEnemy> getEnemies();

    public void addEnemy(IEnemy enemy);
}
