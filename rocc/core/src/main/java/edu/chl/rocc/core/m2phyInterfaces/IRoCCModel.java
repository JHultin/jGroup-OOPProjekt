package edu.chl.rocc.core.m2phyInterfaces;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.Level;
import edu.chl.rocc.core.view.IModel;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public interface IRoCCModel extends IModel {

    public void aim(int x, int y);

    public void constructWorld(TiledMap tMap);

    public void moveSideways(Direction dir);

    public void jump();

    public float getCharacterXPos(int i);

    public float getCharacterYPos(int i);

    public ILevel getLevel();

    public IPlayer getPlayer();

    public void updateWorld(float dt);

    public List<IFood> getFoods();
}
