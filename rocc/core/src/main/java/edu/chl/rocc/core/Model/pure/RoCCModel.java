package edu.chl.rocc.core.model.pure;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.ICollisionListener;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.model.factories.IRoCCFactory;
import edu.chl.rocc.core.model.m2phyInterfaces.*;
import edu.chl.rocc.core.observers.IDeathEvent;
import java.util.List;

/**
 * A class handling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel implements IRoCCModel {

    // The level keeps track of all items in the game
    final ILevel level;

    // Handles all characters and weapons
    final IPlayer player;

    /**
     * Constructor for the model, creates a player and a level defined by a factory
     *
     * @param factory factory defining everything created by the model
     */
    public RoCCModel(IRoCCFactory factory){
        level = factory.createLevel("");
        player = factory.createPlayer("");
    }

    @Override
    public void constructWorld(){

    }

    @Override
    public void constructWorld(IDeathListener listener) {

    }

    @Override
    public void addBlock(IBody body){
        level.addBlock(body);
    }

    @Override
    public void moveSideways(Direction dir){
        player.move(dir);
    }

    @Override
    public void moveFollowers(Direction dir){
        player.moveFollowers(dir);
    }

    @Override
    public boolean characterIsMoving(ICharacter character){
        return character.isMoving();
    }

    @Override
    public void jump() {
        this.player.jump();
    }

    @Override
    public void shoot(float xDir, float yDir){
        this.level.addBullet(this.player.shoot(xDir, yDir));
    }

    @Override
    public float getCharacterXPos(){
        return player.getCharacterXPos();
    }

    @Override
    public float getCharacterYPos(){
        return player.getCharacterYPos();
    }

    /*
    * Adds a character to the character list.
    */
    /*public void addCharacter(ICharacter c){
        this.player.addCharacter(c);
    }*/


    @Override
    public void updateWorld(float dt){
        level.updateWorld(dt);
    }

    @Override
    public List<IPickupable> getPickupables() {
        return level.getPickupables();
    }

    @Override
    public void addPickupable(IPickupable pickup){ level.addPickupable(pickup); }

    /**
     * Removes items from the level using it's removePickupable method
     * @param itemsToRemove list of items that wil be removed
     */
    @Override
    public void removeItems(List<IPickupable> itemsToRemove) {
        for (IPickupable pickup : itemsToRemove){
            if(pickup instanceof IPickupableCharacter){
                this.addCharacter(pickup.getName());
            } else if (pickup instanceof IFood) {
                player.incScore(10);
            }
            level.removePickupable(pickup);
        }
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
    public List<IBullet> getBullets(){
        return this.level.getBullets();
    }

    @Override
    public List<ICharacter> getCharacters() {
        return player.getCharacters();
    }

    @Override
    public List<IEnemy> getEnemies() {
        return level.getEnemies();
    }

    @Override
    public void addEnemy(IEnemy enemy) {
        level.addEnemy(enemy);
    }

    @Override
    public void setCollisionListener(ICollisionListener collisionListener) {

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
    public void changeLead() {
        this.player.cycleActivePlayer();
    }

    @Override
    public void dispose(){
        level.dispose();
        player.dispose();
    }

    @Override
    public int getScore(){
        return player.getScore();
    }

    @Override
    public void incScore(int inc) {
        this.player.incScore(inc);
    }

    @Override
    public int getTime() {
        return level.getTime();
    }


    public void changeDirectionOnEnemies(List<IEnemy> enemyDirToChange) {


    }

    @Override
    public void removeBullets(List<IBullet> bulletsToRemove) {
        for (IBullet bullet : bulletsToRemove){
            level.removeBullet(bullet);
        }
    }

    @Override
    public void addJumpPoint(IJumpPoint jumpPoint) {
        level.addJumpPoint(jumpPoint);
    }

    @Override
    public void addFinish(IFinishPoint finish) {
        level.addFinish(finish);
    }

    @Override
    public void setActiveCharacter(int activeIndex) {
        player.setActiveCharacter(activeIndex);
    }

    @Override
    public ICharacter getActiveCharacter(){
        return this.player.getActiveCharacter();
    }

    @Override
    public void handleDeath(IDeathEvent deathEvent) {
        if (deathEvent.getSource() instanceof ICharacter) {
            player.removeCharacter((ICharacter)deathEvent.getSource());
        } else if (deathEvent.getSource() instanceof IEnemy) {
            player.addToScore(25);
            level.removeEnemy((IEnemy)(deathEvent.getSource()));
        }
    }

}
