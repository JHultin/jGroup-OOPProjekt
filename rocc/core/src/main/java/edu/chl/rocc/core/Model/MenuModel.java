package edu.chl.rocc.core.model;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Jacob on 2015-05-07.
 */
public class MenuModel {

    //A variable to check which menuItem is selected
    private int currentItem;
    private String [] menuItems = {"New Game","Load Game","Options","Highscore","Exit"};


    public void checkInput(String input) {

        if(input.equals("UP")) {
            if (currentItem > 0) {
                currentItem -= 1;
            }
        }else if(input.equals("DOWN")){
            if (currentItem < menuItems.length-1) {
                currentItem += 1;
            }
        }else if(input.equals("ENTER")){
            if(currentItem == 0) {

            }
        }
    }

    /**
     * A method called from the MenuView everytime
     * it renders to check which item is selected.
     * @param index
     * @return
     */
    public boolean isSelected(int index){
        if(index == currentItem){
            return true;
        }
        return false;
    }
}
