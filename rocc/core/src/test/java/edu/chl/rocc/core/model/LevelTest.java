package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IPickupable;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void testAddPickupable() throws Exception {
        Level level = new Level();

        IPickupable pickupableCharacter;
        String[]pickupableCharacters = new String[]{"mother","soldier","doctor"};


        for(int i = 0; i<pickupableCharacters.length; i++){
            pickupableCharacter = new PickupableCharacter(pickupableCharacters[i]);
            level.addPickupable(pickupableCharacter);
        }
        
        assertTrue(level.getPickupables().get(2).getName().equals("doctor"));
    }

    @Test
    public void testRemovePickupable() throws Exception {
        Level level = new Level();

        IPickupable pickupableCharacter = new PickupableCharacter("mother");

        level.addPickupable(pickupableCharacter);

        level.removePickupable(pickupableCharacter);

        assertTrue(!level.getPickupables().contains(pickupableCharacter));
    }

    @Test
    public void testAddJumpPoint() throws Exception {

    }

    @Test
    public void testAddBullet() throws Exception {

    }

    @Test
    public void testRemoveBullet() throws Exception {

    }

    @Test
    public void testAddFinish() throws Exception {

    }

    @Test
    public void testUpdateTime() throws Exception {
        Level level = new Level();

        for(int i = 0; i<=60*2; i++){
            level.updateTime();
        }

        System.out.println(level.getTime());

        assertTrue(level.getTime()==2);

    }
}