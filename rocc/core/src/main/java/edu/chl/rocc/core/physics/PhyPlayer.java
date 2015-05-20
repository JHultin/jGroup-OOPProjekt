package edu.chl.rocc.core.physics;

import static edu.chl.rocc.core.GlobalConstants.PPM;
import com.badlogic.gdx.math.Vector2;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.factories.PhyBulletFactory;
import edu.chl.rocc.core.factories.PhyRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.Player;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyPlayer implements IPlayer {

    private final IPlayer player;
    private final World world;

    private int activeWeaponIndex;
    //private IWeapon weapon;
    private List<IWeapon> weapons;
    //private List<IBullet> bullets;

    public PhyPlayer(World world){
        this.player = new Player(new PhyRoCCFactory(world));
        this.world = world;

        this.activeWeaponIndex = 0;
        this.weapons = new ArrayList<IWeapon>();
        //this.bullets = new ArrayList<IBullet>();
    }

    @Override
    public void jump() {
        this.player.jump();
    }

    @Override
    public void jumpFollower(){
        this.player.jumpFollower();
    }

    @Override
    public void move(Direction dir) {
        this.player.move(dir);
    }

    @Override
    public void moveFollowers(Direction dir){
        this.player.moveFollowers(dir);
    }

    @Override
    public void shoot(float xDir, float yDir){
        this.player.shoot(xDir, yDir);
    }

    @Override
    public void shoot(float x, float y, float xDir, float yDir){
        //createBullet(x, y, xDir, yDir, "");
        this.player.shoot(x, y, xDir, yDir);
    }

    @Override
    public float getCharacterXPos() {
        return this.player.getCharacterXPos();
    }

    @Override
    public float getCharacterYPos() {
        return this.player.getCharacterYPos();
    }

    @Override
    public void addCharacter(String name) {
        this.player.addCharacter(name);
    }

    @Override
    public void addCharacter(String name, IDeathListener listener) {
        this.player.addCharacter(name, listener);
    }

    @Override
    public List<ICharacter> getCharacters() {
        return player.getCharacters();
    }

    @Override
    public void addWeapon(String name){
        this.player.addWeapon(name);
    }

    @Override
    public IWeapon getWeapon(){
        return this.player.getWeapon();
    }

    @Override
    public List<IWeapon> getWeapons(){
        return this.weapons;
    }

    @Override
    public void dispose() {
        player.dispose();
    }

    @Override
    public void setActiveCharacter(int i){
        this.player.setActiveCharacter(i);
    }

    @Override
    public void setFrontCharacter(ICharacter character){
        this.player.setFrontCharacter(character);
    }

    @Override
    public int getFrontCharacterIndex(){
        return this.player.getFrontCharacterIndex();
    }


    @Override
    public float getDistance(int i){
        return this.player.getDistance(i);
    }

    /*
    * Creates and fires a bullet.
    * Temporarily placed in PhyPlayer, will later be moved to a Weapon class.
    */
    /*
    public void createBullet(float x, float y, float xDir, float yDir, String name){
        bullets.add(new PhyBullet(this.world, name, x, y, xDir, yDir));
    }
    */

    @Override
    public void addToScore(int value){
        player.addToScore(value);
    }

    @Override
    public int getScore(){
        return player.getScore();
    }
}
