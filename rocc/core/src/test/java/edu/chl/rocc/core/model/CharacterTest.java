package edu.chl.rocc.core.model;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.observers.IDeathEvent;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {
    private edu.chl.rocc.core.model.Character character;

    @Before
    public void before(){
        character = new Character("mother");
    }

    @After
    public void after(){

    }

    @Test
    public void testGetHP() throws Exception {
        int characterHP = character.getHP();
        assertTrue(characterHP == 100);
    }

    @Test
    public void testSetHP() throws Exception {
        int hPToSet = 50;
        character.setHP(hPToSet);
        assertTrue(character.getHP() == hPToSet);

        hPToSet = -10;
        character.setHP(hPToSet);
        assertFalse(character.getHP() == hPToSet);

        hPToSet = 110;
        character.setHP(hPToSet);
        assertFalse(character.getHP() == hPToSet);
    }

    @Test
    public void testIncHP() throws Exception {
        int fullHP = character.getHP();
        character.decHP(5);
        character.incHP(20);
        assertTrue(character.getHP() == fullHP);

        character.decHP(50);
        int hPBefore = character.getHP();
        int gain = 20;
        character.incHP(gain);

        assertTrue(hPBefore + gain == character.getHP());
    }

    @Test
    public void testDecHP() throws Exception {
        int hPBefore = character.getHP();
        int loss = 20;
        character.decHP(loss);

        assertTrue(hPBefore - loss == character.getHP());

        character.decHP(100);

        assertTrue(character.getHP() == 0);
    }

    @Test
    public void testMove() throws Exception {
        character.move(Direction.RIGHT);
        assertTrue(character.isMoving());
    }

    @Test
    public void testInAir() throws Exception {
        assertFalse(character.inAir());
        character.leftGround();
        assertTrue(character.inAir());
        character.hitGround();
        assertFalse(character.inAir());
    }

    @Test
    public void testGetDirection() throws Exception {
        assertTrue(character.getDirection() == Direction.NONE);
        Direction mDir = Direction.LEFT;
        character.move(mDir);
        assertTrue(character.getDirection().equals(mDir));
    }

    @Test
    public void testIsFollower() throws Exception {
        assertTrue(character.isFollower());
        character.setAsLead();
        assertFalse(character.isFollower());
        character.setAsFollower();
        assertTrue(character.isFollower());
    }

    @Test
    public void testDeath() throws Exception {

        class TestDeathListener implements IDeathListener{
            boolean triggered;

            public boolean hasTriggered(){
                return triggered;
            }

            public void reSetTriggered(){
                triggered = false;
            }

            @Override
            public void deathTriggered(IDeathEvent death) {
                triggered = true;
            }
        }

        TestDeathListener listener = new TestDeathListener();

        character.addDeathListener(listener);
        character.death("dead");
        assertTrue(listener.hasTriggered());

        listener.reSetTriggered();

        character.removeDeathListener(listener);

        character.death("dead again");
        assertFalse(listener.hasTriggered());

        character.removeDeathListener(listener);
    }

    @Test
    public void testGetMoveState() throws Exception {

    }
}