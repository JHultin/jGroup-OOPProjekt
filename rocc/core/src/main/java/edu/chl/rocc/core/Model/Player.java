package edu.chl.rocc.core.model;

import edu.chl.rocc.core.factories.IRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;

import java.lang.*;
import java.lang.Character;
import java.util.ArrayList;
import java.util.List;

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
    public Player(IRoCCFactory factory){

        this.characters = new ArrayList<ICharacter>();
        activeCharacterIndex = 0;

        //Creates the front/main character.
        addCharacter(factory.createCharacter("front", 160, 800));
        //Creates a follower.
        addCharacter(factory.createCharacter("follow", 100, 800));
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
    }

    @Override
    public void move(Direction dir){
        characters.get(0).move(dir);
        moveFollowers(dir);
    }

    @Override
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

    @Override
    public void jump() {
        /*
        for(int i=0; i < characters.size(); i++){
            characters.get(i).jump();
        }
        */
        characters.get(0).jump();
    }

    @Override
    public float getCharacterXPos(int i){
        return characters.get(i).getX();
    }

    @Override
    public float getCharacterYPos(int i){
        return characters.get(i).getY();
    }

    @Override
    public void addCharacter(ICharacter c){
        // skicka in string istället
        characters.add(c);
    }

    @Override
    public List<ICharacter> getCharacters() {
        return characters;
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
