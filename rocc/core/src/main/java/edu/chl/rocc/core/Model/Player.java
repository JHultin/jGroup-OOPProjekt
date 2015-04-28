package edu.chl.rocc.core.model;

import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2015-04-22.
 */
public class Player {

    private Character character;
    private List<Direction> moveList;
    private List<Character> characters;

   // private List<Weapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(World world){
        this.character = new Character(world);

        this.characters = new ArrayList<Character>();
        this.characters.add(this.character);
    }

    public Player(List<Character> characters){

        this.characters = characters;
    }

    public void addToMoveList(Direction direction){
        moveList.add(direction);
    }

    /*
    * Move the front character in a given direction.
    */
    public void move(Direction dir){
        character.move(dir);
    }

    /*
    * Returns the x-coordinate of the character.
    */
    public float getCharacterXPos(){
        return character.getX();
    }

    /*
    * Returns the y-coordinate of the character.
    */
    public float getCharacterYPos(){
        return character.getY();
    }

}
