package edu.chl.rocc.core.model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import edu.chl.rocc.core.RoCCView;
import edu.chl.rocc.core.controller.CollisionListener;
import edu.chl.rocc.core.factories.*;
import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.physics.PhyBullet;
import edu.chl.rocc.core.utility.IDeathEvent;
import org.jbox2d.dynamics.Body;

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
    public void aim(int x, int y){

    }

    @Override
    public void constructWorld(TiledMap tMap){

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
    public boolean frontCharacterIsMoving(){
        return this.player.frontCharacterIsMoving();
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
    public void shoot(float x, float y){
        //level.setAim(x, y);
        level.createBullet(this.getCharacterXPos(), this.getCharacterYPos(), x, y);
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
    public List<IBullet> getBullets(){
        return level.getBullets();
    }

    @Override
    public void addBullet(IBullet bullet) {
        
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

    }

    @Override
    public void handleDeath(IDeathEvent deathEvent) {
        // Temp code
        System.out.println(deathEvent.getMessage());
    }

}
