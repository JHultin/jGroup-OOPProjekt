package edu.chl.rocc.core.physics;

import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.Player;
import org.jbox2d.dynamics.World;

import java.util.List;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyPlayer implements IPlayer {

    private final IPlayer player;
    private final World world;

    public PhyPlayer(World world){
        this.player = new Player(new PhyRoCCFactory(world));
        this.world = world;
    }

    @Override
    public void jump() {
        this.player.jump();
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
    public IBullet shoot(float xDir, float yDir){
        return this.player.shoot(xDir, yDir);
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
    public void removeCharacter(ICharacter character) {
        this.player.removeCharacter(character);
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
    public void removeWeapon(String name){
        this.player.removeWeapon(name);
    }

    @Override
    public IWeapon getWeapon(){
        return this.player.getWeapon();
    }

    @Override
    public List<IWeapon> getWeapons(){
        return this.player.getWeapons();
    }

    @Override
    public void changeWeapon(){
        this.player.changeWeapon();
    }

    @Override
    public int getBulletSpawnX(){
        return this.player.getBulletSpawnX();
    }

    @Override
    public int getBulletSpawnY(){
        return this.player.getBulletSpawnY();
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
    public ICharacter getActiveCharacter(){
        return this.player.getActiveCharacter();
    }

    @Override
    public void cycleActivePlayer(){
        this.player.cycleActivePlayer();
    }

    @Override
    public int getFrontCharacterIndex(){
        return this.player.getFrontCharacterIndex();
    }

    @Override
    public void addToScore(int value){
        player.addToScore(value);
    }

    @Override
    public int getScore(){
        return player.getScore();
    }

    @Override
    public void incScore(int inc) {
        this.player.incScore(inc);
    }
}
