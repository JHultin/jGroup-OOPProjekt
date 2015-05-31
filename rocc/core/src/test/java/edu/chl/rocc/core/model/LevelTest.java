package edu.chl.rocc.core.model;

import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.IFinishPoint;
import edu.chl.rocc.core.m2phyInterfaces.IJumpPoint;
import edu.chl.rocc.core.m2phyInterfaces.IPickupable;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class LevelTest {
    private Level level;

    @Before
    public void before(){
        level = new Level();
    }


    @Test
    public void testAddPickupable() throws Exception {
        IPickupable pickupableCharacter;
        String[]pickupableCharacterNames = new String[]{"mother","soldier","doctor"};

        for(int i = 0; i<pickupableCharacterNames.length; i++){
            pickupableCharacter = new PickupableCharacter(pickupableCharacterNames[i]);
            level.addPickupable(pickupableCharacter);
        }

        assertFalse(level.getPickupables().isEmpty());

        for(int i = 0; i<pickupableCharacterNames.length; i++){
            assertTrue(level.getPickupables().get(i).getName().equals(pickupableCharacterNames[i]));
        }
    }

    @Test
    public void testRemovePickupable() throws Exception {
        IPickupable pickupableCharacter = new PickupableCharacter("mother");

        level.addPickupable(pickupableCharacter);

        level.removePickupable(pickupableCharacter);

        assertTrue(level.getPickupables().isEmpty());
        assertFalse(level.getPickupables().contains(pickupableCharacter));
    }

    @Test
    public void testAddJumpPoint() throws Exception {
        IJumpPoint jumpPoint = new JumpPoint();
        level.addJumpPoint(jumpPoint);
        //This method didn't have anything to confirm if jumpPoint is added.
    }

    @Test
    public void testAddBullet() throws Exception {
        IBullet[] bullet = new Bullet[5];
        for(int i = 0; i<5; i++) {
            bullet[i] = new Bullet("bullet"+i,0,0);
            level.addBullet(bullet[i]);
        }

        assertFalse(level.getBullets().isEmpty());

        for(int i = 0; i<5; i++){
            assertTrue(level.getBullets().contains(bullet[i]));
        }

    }

    @Test
    public void testRemoveBullet() throws Exception {
        IBullet bullet = new Bullet("bullet",0,0);
        level.addBullet(bullet);

        level.removeBullet(bullet);

        assertFalse(level.getBullets().contains(bullet));
        assertTrue(level.getBullets().isEmpty());
    }

    @Test
    public void testAddFinish() throws Exception {
        IFinishPoint finish = new FinishPoint();
        level.addFinish(finish);
        //This method didn't have anything to confirm if finish is added.
    }

    @Test
    public void testUpdateTime() throws Exception {
        for(int i = 0; i<=2 * 60 + 2; i++){
            level.updateTime(1f / 60);
        }

        assertTrue(level.getTime()==2);

    }
}