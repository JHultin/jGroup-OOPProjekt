package edu.chl.rocc.core.model;

import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.factories.BulletFactory;
import edu.chl.rocc.core.factories.IRoCCFactory;
import edu.chl.rocc.core.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.m2phyInterfaces.IWeapon;
import java.lang.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jacob on 2015-04-22.
 */
public class Player implements IPlayer {

    private final List<ICharacter> characters;
    private IRoCCFactory factory;

    //Index of the active character in list 'characters'
    private int activeCharacterIndex;

    private int score;

    //private IWeapon weapon;
    private List<IWeapon> weapons;
    private int activeWeaponIndex;
    private List<IBullet> bullets;

    /*
    * Constructor creating a single character and adds it to the character list.
    */
    public Player(IRoCCFactory factory){

        this.characters = new ArrayList<ICharacter>();
        this.activeCharacterIndex = 0;
        this.factory = factory;

        this.score = 0;

        //this.weapon = new Weapon(new BulletFactory());

        this.weapons = new ArrayList<IWeapon>();
        this.activeWeaponIndex = 0;
    }

    public Player(List<ICharacter> characters){

        this.characters = characters;
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

                    if (distance > 20 + count * 60) {
                        characters.get(i).move(Direction.RIGHT);
                    } else if (distance < -(20 + count * 60)) {
                        characters.get(i).move(Direction.LEFT);
                    } else {
                        characters.get(i).move(Direction.NONE);
                    }
                }
            }
        } else{
            for (int k=0; k < characters.size(); k++) {
                characters.get(k).move(Direction.NONE);
            }
        }
    }

    @Override
    public boolean frontCharacterIsMoving(){
        return characters.get(activeCharacterIndex).isMoving();
    }

    @Override
    public void jump() {
        characters.get(this.activeCharacterIndex).jump();
    }

    @Override
    public void jumpFollower(){
        for(int i=0; i<characters.size(); i++){
            characters.get(i).jumpIfFollower();
        }
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
                ICharacter character = this.factory.createCharacter(name, 160, 400);
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
    public List<ICharacter> getCharacters() {
        return characters;
    }

    @Override
    public void addWeapon(String name){
        //this.weapons.add(weapon);

        synchronized (weapons) {
            weapons.add(this.factory.createWeapon(name, 160, 400));
            /*if (weapons.isEmpty()) {
                weapons.add(this.factory.createWeapon(name, 160, 400));
            } else {
                weapons.add(this.factory.createWeapon(name,
                        characters.get(this.activeCharacterIndex).getX(),
                        characters.get(this.activeCharacterIndex).getY()+16));
            }
            */
        }
    }

    @Override
    public IWeapon getWeapon(){
        return this.weapons.get(activeWeaponIndex);
    }

    @Override
    public void shoot(float x, float y, float xDir, float yDir){
        this.weapons.get(activeWeaponIndex).createBullet(x, y, xDir, yDir);
    }

    @Override
    public void dispose() {
        this.weapons.get(activeWeaponIndex).dispose();

        for(ICharacter character : characters){
            character.dispose();
        }
        /*
        for (IBullet bullet : bullets){
            bullet.dispose();
        }
        */
    }

    @Override
    public void setActiveCharacter(int i){
        this.characters.get(activeCharacterIndex).setAsFollower();
        this.activeCharacterIndex = i;
        this.characters.get(i).setAsLead();
    }

    /*
    * Returns the distance between the front character and a follower.
    */
    public float getDistance(int i){
        return Math.abs(characters.get(i).getX() - characters.get(0).getX());
    }

    /*
    * Returns the x-direction towards the front character from a follower.
    */
    public Direction getDirection(int i){
        if(characters.get(0).getX() - characters.get(i).getX() > 0){
            return Direction.RIGHT;
        } else if(characters.get(0).getX() - characters.get(i).getX() < 0){
            return Direction.LEFT;
        } else{
            return Direction.NONE;
        }
    }

    @Override
    public void addToScore(int value){
        this.score +=value;
    }

    @Override
    public int getScore(){
        return this.score;
    }

}
