package edu.chl.rocc.core.model;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoCCModelTest {
    private RoCCModel model;

    @Before
    public void before(){
        this.model = new RoCCModel(new RoCCFactory());
        this.model.addCharacter("mother");
        this.model.addCharacter("soldier");
        this.model.addCharacter("doctor");
    }

    @Test
    public void testCreatesCharacter(){
        assertTrue(this.model.getCharacters().size() == 3);
    }

    @Test
    public void testCreatesLevel(){
        assertFalse(this.model.level == null);
    }

    @Test
    public void testCreatesPlayer(){
        assertFalse(this.model.player == null);
    }

    @Test
    public void testKillCharacter() throws Exception {

    }

    @Test
    public void testMoveSideways() throws Exception {
        ICharacter frontCharacter = this.model.getCharacters().get(0);

        this.model.moveSideways(Direction.RIGHT);
        assertTrue(this.model.characterIsMoving(frontCharacter));
    }

    @Test
    public void testTimer() throws Exception {
        float updateSpeed = 1 / 60f;
        int timeBefore = this.model.getTime();

        for(int i=0; i<=60 + 1; i++){
            this.model.updateWorld(updateSpeed);
        }
        assertFalse(this.model.getTime() == timeBefore);
    }
}