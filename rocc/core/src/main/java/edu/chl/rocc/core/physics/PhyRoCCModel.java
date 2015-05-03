package edu.chl.rocc.core.physics;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.Level;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyRoCCModel implements IRoCCModel {

    private IRoCCModel model;

    @Override
    public void aim(int x, int y) {
        this.model.aim(x, y);
    }

    @Override
    public void constructWorld(TiledMap tMap) {
        this.model.constructWorld(tMap);
    }

    @Override
    public void moveSideways(Direction dir) {
        this.model.moveSideways(dir);
    }

    @Override
    public void jump() {
        this.model.jump();
    }

    @Override
    public float getCharacterXPos() {
        return this.model.getCharacterXPos();
    }

    @Override
    public float getCharacterYPos() {
        return this.model.getCharacterYPos();
    }

    @Override
    public Level getLevel() {
        return this.model.getLevel();
    }

    @Override
    public void updateWorld(float dt) {
        this.model.updateWorld(dt);
    }
}
