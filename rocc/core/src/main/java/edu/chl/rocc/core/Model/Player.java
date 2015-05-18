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
    private IRoCCFactory factory;

    private int activePlayerIndex;
    //Index of the active character in list 'characters'
    private int activeCharacterIndex;

   // private List<Weapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(IRoCCFactory factory){

        this.characters = new ArrayList<ICharacter>();
        this.activeCharacterIndex = 0;
        this.factory = factory;
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
    }

    @Override
    public void move(Direction dir) {
        if (this.activeCharacterIndex < characters.size()){
            characters.get(this.activeCharacterIndex).move(dir);
            characters.get(this.activeCharacterIndex).setCurrentDirection(dir);
        }
        //moveFollowers(dir);
    }

    @Override
    public void moveFollowers(Direction dir){
        if(dir != Direction.NONE) {
            int count = 0;
            for (int i=0; i < characters.size(); i++) {
                if (i != activeCharacterIndex) {
                    count ++;
                    float distance = characters.get(this.activeCharacterIndex).getX() - characters.get(i).getX();

                    if (distance > 20 + count * 60) {
                        characters.get(i).moveFollower(Direction.RIGHT);
                    } else if (distance < -(20 + count * 60)) {
                        characters.get(i).moveFollower(Direction.LEFT);
                    } else {
                        characters.get(i).moveFollower(Direction.NONE);
                    }
                }
            }
        } else{
            for (int k=1; k < characters.size(); k++) {
                characters.get(k).moveFollower(Direction.NONE);
            }
        }
    }

    @Override
    public boolean frontCharacterIsMoving(){
        return characters.get(activeCharacterIndex).isMoving();
    }

    @Override
    public void jump() {
        /*
        for(int i=0; i < characters.size(); i++){
            characters.get(i).jump();
        }
        */
        characters.get(this.activeCharacterIndex).jump();
    }

    @Override
    public void jumpFollower(){
        for(int i=0; i<characters.size(); i++){
            characters.get(i).jumpIfFollower();
        }
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
    public void addCharacter(String name) {
        if (characters.isEmpty()){
            characters.add(this.factory.createCharacter(name, 160, 400));
        } else {
            characters.add(this.factory.createCharacter(name, characters.get(this.activeCharacterIndex).getX(),
                    characters.get(this.activeCharacterIndex).getY()));
        }
    }

    @Override
    public List<ICharacter> getCharacters() {
        return characters;
    }

    @Override
    public void dispose() {
        for(ICharacter character : characters){
            character.dispose();
        }
    }

    @Override
    public void setActiveCharacter(int i){
        this.characters.get(activeCharacterIndex).setAsFollower();
        this.activeCharacterIndex = i;
        this.characters.get(i).setAsLead();
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
