package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.factories.ILevelFactory;
import edu.chl.rocc.core.factories.IPlayerFactory;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.controller.MyContactListener;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;


/**
 * A class handeling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel implements IRoCCModel {


    private ILevel level;
    private IPlayer player;
    private MyContactListener listener = new MyContactListener();


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

    public void jump() {
        player.jump();
    }

    public float getCharacterXPos(){
        return player.getCharacterXPos();
    }

    public float getCharacterYPos(){
        return player.getCharacterYPos();
    }

    public ILevel getLevel(){
        return level;
    }

    public void updateWorld(float dt){level.updateWorld(dt); }


}
