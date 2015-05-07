package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.factories.ICharacterFactory;
import edu.chl.rocc.core.factories.ILevelFactory;
import edu.chl.rocc.core.factories.IPlayerFactory;
import edu.chl.rocc.core.factories.PhyCharacterFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;


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

    /*
    * Move the character in a given direction.
    */

    public void moveSideways(Direction dir){
        player.move(dir);
    }

    /*
    * Make the character jump by changing its y-coordinate.
    */

    public void jump() {
        player.jump();
    }

    /*
    * Returns the x-coordinate of the given character.
    */
    public float getCharacterXPos(int i){
        return player.getCharacterXPos(i);
    }


    /*
    * Returns the y-coordinate of the given character.
    */
    public float getCharacterYPos(int i){
        return player.getCharacterYPos(i);
    }

    /*
    * Adds a character to the character list.
    */
    public void addCharacter(ICharacter c){
        this.player.addCharacter(c);
    }

    /*
    * Returns the level.
    */
    public ILevel getLevel(){
        return level;
    }

    /*
    * Returns the player.
    */
    public IPlayer getPlayer(){
        return this.player;
    }

    public void updateWorld(float dt){level.updateWorld(dt); }


}
