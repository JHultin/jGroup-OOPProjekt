package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.ICharacterFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.physics.PhyCharacter;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2015-04-22.
 */
public class Player implements IPlayer {

    private List<ICharacter> characters;

   // private List<Weapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(ICharacterFactory characterFactory, World world){

        this.characters = new ArrayList<ICharacter>();
        this.characters.add(characterFactory.createCharacter(""));

    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
    }

    /*
    * Move the front character in a given direction.
    */
    public void move(Direction dir){
        characters.get(0).move(dir);
    }

    public void jump() { characters.get(0).jump(); }

    /*
    * Returns the x-coordinate of the character.
    */
    public float getCharacterXPos(){
        return characters.get(0).getX();
    }

    /*
    * Returns the y-coordinate of the character.
    */
    public float getCharacterYPos(){
        return characters.get(0).getY();
    }

}
