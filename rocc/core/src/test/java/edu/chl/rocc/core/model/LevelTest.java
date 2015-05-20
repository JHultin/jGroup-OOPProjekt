package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IPickupable;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {

    @Test
    public void testAddPickupable() throws Exception {

    }

    @Test
    public void testRemovePickupable() throws Exception {

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