package edu.chl.rocc.core.model;

import edu.chl.rocc.core.controller.CollisionListener;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.factories.IRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.utility.IDeathEvent;

import java.util.List;


/**
 * A class handling the game model.
 *
 * Created by Yen on 2015-04-22.
 */
public class RoCCModel implements IRoCCModel {

    // The level keeps track of all items in the game
    private ILevel level;

    // Handles all characters and weapons
    private IPlayer player;
    private IWeapon weapon;

    /**
     * Constructor for the model, creates a player and a level defined by a factory
     *
     * @param factory factory defining everything created by the model
     */
    public RoCCModel(IRoCCFactory factory){
        level = factory.createLevel("");
        player = factory.createPlayer("");
        //this.weapon = player.getWeapon();
    }

    @Override
    public void aim(int x, int y){

    }

    @Override
    public void constructWorld(){

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
        player.jump();
    }

    @Override
    public void jumpFollowerIfPossible(){
        for(int i=0; i<this.player.getCharacters().size(); i++){
            if(this.characterIsMoving(this.player.getCharacters().get(i))){
                this.player.getCharacters().get(i).jumpIfFollower();
            }
        }
    }

    @Override
    public void shoot(float xDir, float yDir){
        this.player.shoot(xDir, yDir);
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
    public ILevel getLevel(){
        return level;
    }

    @Override
    public IPlayer getPlayer(){
        return this.player;
    }

    @Override
    public void updateWorld(float dt){
        level.updateWorld(dt);
    }

    @Override
    public List<IPickupable> getPickupables() {
        return level.getPickupables();
    }

    /**
     * Removes items from the level using it's removePickupable method
     * @param itemsToRemove list of items that wil be removed
     */
    @Override
    public void removeItems(List<IPickupable> itemsToRemove) {
        for (IPickupable pickup : itemsToRemove){
            level.removePickupable(pickup);
        }
    }

    @Override
    public IWeapon getWeapon(){
        return this.player.getWeapon();
    }

    @Override
    public List<IBullet> getBullets(){
        return level.getBullets();
    }

    @Override
    public void addBullet(IBullet bullet) {
        
    }

    @Override
    public void addWeapon(String name){
        this.player.addWeapon(name);
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
    public void setCollisionListener(CollisionListener collisionListener) {

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
        this.player.setActiveCharacter((int)(Math.random() * this.player.getCharacters().size()));
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
    public void handleDeath(IDeathEvent deathEvent) {
        // Temp code
        System.out.println(deathEvent.getMessage());
    }

}
