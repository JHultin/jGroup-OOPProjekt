package edu.chl.rocc.core.model;

import edu.chl.rocc.core.view.IModel;

/**
 * Created by Jacob on 2015-05-07.
 */
public class MenuModel implements IModel{
        //A variable to check which menuItem is selected
        private int currentItem;
        private String [] menuItems = {"New Game","Load Game","Options","Highscore","Exit"};


        public void navigate(Direction direction) {

            if (direction.equals(Direction.UP)) {
                if (currentItem > 0) {
                    currentItem -= 1;
                }
            } else if (direction.equals(Direction.DOWN)) {
                if (currentItem < menuItems.length - 1) {
                    currentItem += 1;
                }
            }
        }

        public void select(){
            if(currentItem == 0) {
                System.out.println("ENTER NEW GAME");
            }else if(currentItem == 1){
                System.out.println("ENTER LOAD GAME");
            }else if(currentItem == 2){
                System.out.println("ENTER OPTIONS");
            }else if(currentItem == 3){
                System.out.println("ENTER HIGHSCORE");
            }else if(currentItem == 4){
                System.out.println("ENTER EXIT");
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

        public String[] getMenuItems(){
            return menuItems;
        }
}
