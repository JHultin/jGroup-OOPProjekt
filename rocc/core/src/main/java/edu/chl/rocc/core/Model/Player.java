package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.ICharacterFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import org.jbox2d.common.Vec2;
import edu.chl.rocc.core.physics.PhyCharacter;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

import static edu.chl.rocc.core.GlobalConstants.PPM;

/**
 * Created by Jacob on 2015-04-22.
 */
public class Player implements IPlayer {

    private final List<ICharacter> characters;

    private int activePlayerIndex;
    //Index of the active character in list 'characters'
    private int activeCharacterIndex;

   // private List<Weapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(ICharacterFactory characterFactory, World world){

        this.characters = new ArrayList<ICharacter>();
        activeCharacterIndex = 0;

        //Creates the front/main character.
        addCharacter(characterFactory.createCharacter("firstCharacter", 160, 800));
        //Creates a follower.
        addCharacter(characterFactory.createCharacter("secondCharacter", 100, 800));
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
    }

    /*
    * Move the front character in a given direction.
    */
    public void move(Direction dir){
        characters.get(0).move(dir);
        moveFollowers(dir);
    }

    /*
    * Move the follower characters towards the front character.
    */
    public void moveFollowers(Direction dir){
        if(dir != Direction.NONE) {

            for (int i=1; i < characters.size(); i++) {

            /*
            if(getDistance(i) > 200){
                characters.get(i).moveFollower(dir);
            } else{
                characters.get(i).moveFollower(Direction.NONE);
            }
            */
                float distance = characters.get(0).getX() - characters.get(i).getX();

                if (distance > 80) {
                    characters.get(i).moveFollower(Direction.RIGHT);
                } else if (distance < -80) {
                    characters.get(i).moveFollower(Direction.LEFT);
                    //characters.get(i).moveFollower(Direction.NONE);
                } else {
                    characters.get(i).moveFollower(Direction.NONE);
                }
            }
        } else{
            for (int k=1; k < characters.size(); k++) {
                characters.get(k).moveFollower(Direction.NONE);
            }
        }
    }

    public void jump() {
        /*
        for(int i=0; i < characters.size(); i++){
            characters.get(i).jump();
        }
        */
        characters.get(0).jump();
    }

    /*
    * Returns the x-coordinate of the character.
    */
    public float getCharacterXPos(int i){
        return characters.get(i).getX();
    }

    /*
    * Returns the y-coordinate of the character.
    */
    public float getCharacterYPos(int i){
        return characters.get(i).getY();
    }

    /*
    * Adds a character to the character list.
    */
    public void addCharacter(ICharacter c){
        // skicka in string istället
        characters.add(c);
    }
    /*
    * Change which character the player is playing as.
    */
    public void changeActiveCharacter(){
        if(activeCharacterIndex++ < characters.size()){
            activeCharacterIndex++;
        } else{
            activeCharacterIndex = 0;
        }
    }

    /*
    * Returns the distance between the front character and a follower.
    */
    public float getDistance(int i){
        return Math.abs(characters.get(i).getX() - characters.get(0).getX());
    }

    /*
    * Returns the x-direction towards the front character from a follower.
    */
    public Direction getDirection(int i){
        if(characters.get(0).getX() - characters.get(i).getX() > 0){
            return Direction.RIGHT;
        } else if(characters.get(0).getX() - characters.get(i).getX() < 0){
            return Direction.LEFT;
        } else{
            return Direction.NONE;
        }
    }

}
