package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.ICharacterFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import org.jbox2d.common.Vec2;
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

        for(int i=0; i < characters.size(); i++){
            characters.get(i).move(dir);
        }
        //character.move(dir);
    }

    public void jump() {

        for(int i=0; i < characters.size(); i++){
            characters.get(i).jump();
        }
        //characters.get(0).jump();
    }

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

    /*
    * Returns the x-coordinate of the chosen follower.
    */
    public float getFollowerXPos(int i){
        return characters.get(i).getX();
    }

    /*
    * Returns the y-coordinate of the chosen follower.
    */
    public float getFollowerYPos(int i){
        return characters.get(i).getY();
    }

    /*
    * Adds a character to the character list.
    */
    public void addCharacter(Character c){
        characters.add(c);
    }

    public void changeActiveCharacter(Character c){
        //this.character = characters.get(characters.indexOf(c) + 1);
    }

}
