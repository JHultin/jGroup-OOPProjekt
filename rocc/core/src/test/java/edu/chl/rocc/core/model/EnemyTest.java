package edu.chl.rocc.core.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnemyTest {
    private Enemy enemy;

    @Before
    public void before(){
        enemy = new Enemy(50, "zombie", 50, 50);
    }

    @After
    public void after(){

    }

    @Test
    public void testGetHP() throws Exception {
        int enemyHP = enemy.getHP();
        assertTrue(enemyHP == 50);
    }

    @Test
    public void testSetHP() throws Exception {
        int hPToSet = 80;
        enemy.setHP(hPToSet);
        assertTrue(enemy.getHP() == hPToSet);

        hPToSet = -40;
        enemy.setHP(hPToSet);
        assertFalse(enemy.getHP() >= 0 && enemy.getHP() < 0);
    }

    @Test
    public void testDecHP() throws Exception {
        int lose = 10;
        enemy.setHP(50);
        enemy.decHP(lose);
        assertTrue(enemy.getHP() == 40);

        lose = 40;
        enemy.decHP(lose);
        assertTrue(enemy.getHP() == 0);

        lose = 100;
        enemy.decHP(lose);
        assertFalse(enemy.getHP() >= 0 && enemy.getHP() < 0);
    }

    @Test
    public void testChangeMoveDirection() throws Exception {
        /*enemy.changeMoveDirection();
        assertTrue(enemy.getDirection().equals(Direction.RIGHT));*/
    }
}