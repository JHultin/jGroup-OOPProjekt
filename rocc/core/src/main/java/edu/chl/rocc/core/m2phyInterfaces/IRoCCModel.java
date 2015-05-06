package edu.chl.rocc.core.m2phyInterfaces;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.Level;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel {

    public void aim(int x, int y);

    public void constructWorld(TiledMap tMap);

    public void moveSideways(Direction dir);

    public void jump();

    public float getCharacterXPos(int i);

    public float getCharacterYPos(int i);

    public ILevel getLevel();

    public void updateWorld(float dt);
}
