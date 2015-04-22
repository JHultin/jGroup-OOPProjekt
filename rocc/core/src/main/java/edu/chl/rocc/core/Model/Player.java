package edu.chl.rocc.core.Model;

import java.util.List;

/**
 * Created by Jacob on 2015-04-22.
 */
public class Player {

    private List<Direction> moveList;

    private List<Character> characters;

   // private List<Weapon> weapons;



    public Player(List<Character> characters){

        this.characters = characters;

    }




    public void addToMoveList(Direction direction){
        moveList.add(direction);
    }

}
