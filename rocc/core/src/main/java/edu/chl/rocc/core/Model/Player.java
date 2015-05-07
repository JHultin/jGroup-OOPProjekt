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

    private int activePlayerIndex;
    private List<ICharacter> characters;

   // private List<Weapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(ICharacterFactory characterFactory, World world){

        activePlayerIndex = 0;
        this.characters = new ArrayList<ICharacter>();
        addCharacter(characterFactory.createCharacter("", 160, 400));
        addCharacter(characterFactory.createCharacter("", 100, 400));
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
    }

    /*
    * Move the front character in a given direction.
    */
    public void move(Direction dir){
        characters.get(0).move(dir);

        for(int i=1; i < characters.size(); i++){
            if(Math.abs(characters.get(i).getX() - characters.get(0).getX()) > 120 / PPM){

                if(characters.get(0).getX() - characters.get(i).getX() > 0){
                    characters.get(i).moveFollower(1);
                } else{
                    characters.get(i).moveFollower(-1);
                }

            }
        }
        //character.move(dir);
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
        if(activePlayerIndex++ < characters.size()){
            activePlayerIndex++;
        } else{
            activePlayerIndex = 0;
        }
    }

}
