package edu.chl.rocc.core.model.pure;

import edu.chl.rocc.core.Direction;
import edu.chl.rocc.core.model.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.model.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.model.pure.Player;
import edu.chl.rocc.core.model.pure.RoCCFactory;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;

    @Before
    public void before(){
        player = new Player(new RoCCFactory());
    }

    @Test
    public void testMoveFollowers() throws Exception {
        ICharacter character;

        this.player.addCharacter("mother");
        this.player.addCharacter("doctor");
        this.player.addCharacter("soldier");

        for(int i=0; i<this.player.getCharacters().size(); i++){
            character = this.player.getCharacters().get(i);

            if(character.isFollower()){
                character.move(Direction.RIGHT);
                assertTrue(character.isMoving());
            }
        }
    }

    @Test
    public void testCharacterList() throws Exception {
        assertTrue(this.player.getCharacters().size() == 0);

        this.player.addCharacter("mother");
        this.player.addCharacter("doctor");
        this.player.addCharacter("soldier");

        assertTrue(this.player.getCharacters().size() == 3);
    }

    @Test
    public void testWeapon() throws Exception {
        assertTrue(this.player.getWeapons().size() == 0);

        this.player.addWeapon("weapon");
        assertTrue(this.player.getWeapons().size() == 1);

        assertTrue(this.player.getWeapon() instanceof IWeapon);
        assertTrue(this.player.getWeapon().equals(this.player.getWeapon()));

        assertTrue(this.player.getWeapons() instanceof List);
        assertTrue(this.player.getWeapons().equals(this.player.getWeapons()));
    }

    /*
     * Är det bättre att dela upp testerna i olika metoder?
     * Har man mycket i samma kan man slippa att ha samma kod på flera ställen, värt?
     *
    @Test
    public void testAddWeapon() throws Exception {
        assertTrue(this.player.getWeapons().size() == 0);

        this.player.addWeapon("weapon");
        assertTrue(this.player.getWeapons().size() == 1);
    }

    @Test
    public void testGetWeapon() throws Exception {
        this.player.addWeapon("weapon");
        assertTrue(this.player.getWeapon() instanceof IWeapon);
    }

    @Test
    public void testGetWeapons() throws Exception {
        this.player.addWeapon("weapon");
        assertTrue(this.player.getWeapons() instanceof List);
    }
    */

    @Test
    public void testActiveCharacter() throws Exception {
        int activeChar = 1;

        this.player.addCharacter("mother");
        this.player.addCharacter("doctor");

        this.player.setActiveCharacter(activeChar);
        assertTrue(this.player.getFrontCharacterIndex() == activeChar);
    }

    @Test
    public void testScore() throws Exception {
        int value = 10;

        // Check if score starts at zero points.
        assertTrue(this.player.getScore() == 0);

        this.player.addToScore(value);
        assertTrue(this.player.getScore() == value);

        this.player.addToScore(-value);
        assertTrue(this.player.getScore() == 0);

        // Assuming player score can't be negative.
        this.player.addToScore(-value);
        assertFalse(this.player.getScore() < 0);
    }
}