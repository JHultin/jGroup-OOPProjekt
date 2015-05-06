package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import edu.chl.rocc.core.factories.ILevelFactory;
import edu.chl.rocc.core.factories.IPlayerFactory;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;


/**
 * A class handeling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel implements IRoCCModel {

    private ILevel level;
    private IPlayer player;

    public RoCCModel(ILevelFactory levelFactory, IPlayerFactory playerFactory){
        level = levelFactory.createLevel("");
        player = playerFactory.createPlayer("");
    }

    public void aim(int x, int y){

    }

    // Creates a logical box2d map mimicing the tiled-map
    public void constructWorld(TiledMap tMap){

    }

    public void moveSideways(Direction dir){
        player.move(dir);
    }

    public void jump() { player.jump(); }

    public float getCharacterXPos(){
        return player.getCharacterXPos();
    }

    public float getCharacterYPos(){
        return player.getCharacterYPos();
    }

    public float getFollowerXPos(int i){
        return player.getFollowerXPos(i);
    }

    public float getFollowerYPos(int i){
        return player.getFollowerYPos(i);
    }

    public ILevel getLevel(){
        return level;
    }

    public void updateWorld(float dt){level.updateWorld(dt); }


}
