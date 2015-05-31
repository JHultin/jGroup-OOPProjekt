package edu.chl.rocc.core.model;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.factories.IRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Class handeling the player.
 * <br>Implements IPlayer.
 *
 * Created by Jacob on 2015-04-22.
 */
public class Player implements IPlayer {

    private final List<ICharacter> characters;
    private IRoCCFactory factory;

    //Index of the active character in list 'characters'
    private int activeCharacterIndex;

    private int score;

    //Index of active/wielded weapon in list 'weapons'
    private int activeWeaponIndex;
    private final List<IWeapon> weapons;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(IRoCCFactory factory){

        this.characters = new ArrayList<ICharacter>();
        this.activeCharacterIndex = 0;
        this.factory = factory;

        this.score = 0;

        this.activeWeaponIndex = 0;
        this.weapons = new ArrayList<IWeapon>();
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
        this.weapons = new ArrayList<IWeapon>();
    }

    @Override
    public void move(Direction dir) {
        if (this.activeCharacterIndex < characters.size()){
            characters.get(this.activeCharacterIndex).move(dir);
        }
    }

    @Override
    public void moveFollowers(Direction dir){
        if(dir != Direction.NONE) {
            int count = 0;
            for (int i=0; i < characters.size(); i++) {
                if (i != activeCharacterIndex) {
                    count ++;
                    float distance = characters.get(this.activeCharacterIndex).getX() - characters.get(i).getX();

                    // Check if the follower is very far away from the active character.
                    // If yes, teleport it to the active character.
                    if(Math.abs(distance) >= 450){
                        characters.get(i).teleport(getCharacterXPos(), getCharacterYPos());
                    }
                    // Check if the follower is far away enough from the active character.
                    // If yes, move towards the active character. If no, stand still.
                    else if (distance > 20 + count * 60) {
                        characters.get(i).move(Direction.RIGHT);
                    } else if (distance < -(20 + count * 60)) {
                        characters.get(i).move(Direction.LEFT);
                    } else {
                        characters.get(i).move(Direction.NONE);
                    }
                }
            }
        } else{
            for (ICharacter character : characters) {
                character.move(Direction.NONE);
            }
        }
    }

    @Override
    public void jump() {
        characters.get(this.activeCharacterIndex).jump();
    }

    @Override
    public float getCharacterXPos(){
        return characters.get(activeCharacterIndex).getX();
    }

    @Override
    public float getCharacterYPos(){
        return characters.get(activeCharacterIndex).getY();
    }

    @Override
    public void addCharacter(String name) {
        synchronized (characters) {
            if (characters.isEmpty()) {
                characters.add(this.factory.createCharacter(name, 160, 400));
            } else {
                characters.add(this.factory.createCharacter(name,
                        characters.get(this.activeCharacterIndex).getX(),
                        characters.get(this.activeCharacterIndex).getY()+16));
            }
        }
    }

    @Override
    public void addCharacter(String name, IDeathListener listener) {
        synchronized (characters) {
            if (characters.isEmpty()) {
                ICharacter character = this.factory.createCharacter(name, 1200, 1200);
                character.addDeathListener(listener);
                characters.add(character);
            } else {
                ICharacter character = this.factory.createCharacter(name,
                        characters.get(this.activeCharacterIndex).getX(),
                        characters.get(this.activeCharacterIndex).getY()+16);
                character.addDeathListener(listener);
                characters.add(character);
            }
        }
    }

    @Override
    public void removeCharacter(ICharacter character) {
        character.dispose();
        if (characters.size() > 1) {
            characters.remove(character);
            activeCharacterIndex %= characters.size();
            this.setActiveCharacter(activeCharacterIndex);
        }
    }

    @Override
    public List<ICharacter> getCharacters() {
        return characters;
    }

    @Override
    public void addWeapon(String name){
        synchronized (weapons) {
            weapons.add(this.factory.createWeapon(name, this.getCharacterXPos(), this.getCharacterYPos()));
        }
    }

    @Override
    public void removeWeapon(String name){

    }

    @Override
    public IWeapon getWeapon(){
        return this.weapons.get(activeWeaponIndex);
    }

    @Override
    public List<IWeapon> getWeapons(){
        return this.weapons;
    }

    @Override
    public void changeWeapon(){
        this.activeWeaponIndex = this.getActiveCharacter().getWeapon();
    }

    @Override
    public int getBulletSpawnX(){
        return this.weapons.get(activeWeaponIndex).getBulletSpawnX();
    }

    @Override
    public int getBulletSpawnY(){
        return this.weapons.get(activeWeaponIndex).getBulletSpawnY();
    }

    @Override
    public IBullet shoot(float xDir, float yDir){
        return this.getWeapon().createBullet(this.getCharacterXPos(), this.getCharacterYPos(), xDir, yDir);
    }

    @Override
    public void dispose() {
        this.weapons.get(activeWeaponIndex).dispose();

        for(ICharacter character : characters){
            character.dispose();
        }
    }

    @Override
    public void setActiveCharacter(int i){
        this.characters.get(activeCharacterIndex).setAsFollower();
        this.activeCharacterIndex = i;
        this.characters.get(i).setAsLead();
    }

    @Override
    public ICharacter getActiveCharacter(){
        return this.characters.get(activeCharacterIndex);
    }

    @Override
    public void cycleActiveCharacter() {
        this.setActiveCharacter((activeCharacterIndex + 1) % characters.size());
    }

    @Override
    public int getFrontCharacterIndex(){
        return activeCharacterIndex;
    }

    @Override
    public void addToScore(int value){
        this.score = Math.max(score + value, 0);
    }

    @Override
    public int getScore(){
        return this.score;
    }

    @Override
    public void incScore(int inc) {
        this.score += inc;
    }

}
