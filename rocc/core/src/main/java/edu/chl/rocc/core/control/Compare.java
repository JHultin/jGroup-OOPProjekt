package edu.chl.rocc.core.control;

/**
 * Class containing comparation methods.
 *
 * @author Jenny Orell
 */
public class Compare {

    /*
    * Checks if the given value is negative.
    * If yes, returns true.
    */
    public static boolean isNegative(int i){
        return i < 0;
    }

    /*
    * Checks if the given value is equal to zero.
    * If yes, returns true.
    */
    public static boolean isZero(int i){
        return i == 0;
    }

    /*
    * Checks if a given value is greater than another.
    * If yes, returns true.
    */
    public static boolean greaterThan(int a, int b){
        return a > b;
    }

}
