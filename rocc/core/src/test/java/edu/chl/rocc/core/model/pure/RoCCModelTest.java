package edu.chl.rocc.core.model.pure;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.model.m2phyInterfaces.ICharacter;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoCCModelTest {
    private RoCCModel model;

    @Before
    public void before(){
        model = new RoCCModel(new RoCCFactory());
    }

    @Test
    public void testCreatesCharacter(){
        assertFalse(this.model.getCharacters().isEmpty());
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
    public void testMoveFollowers() throws Exception {
        ICharacter follower;

        this.model.moveFollowers(Direction.RIGHT);
        for(int i=0; i<this.model.getCharacters().size(); i++){
            follower = this.model.getCharacters().get(i);
            assertTrue(this.model.characterIsMoving(follower));
        }
    }

    @Test
    public void testTimer() throws Exception {
        float updateSpeed = 1 / 60f;
        int timeBefore = this.model.getTime();

        this.model.updateWorld(updateSpeed);
        assertFalse(this.model.getTime() == timeBefore);
    }
}