package edu.chl.rocc.core.model.pure;

import edu.chl.rocc.core.utility.Direction;
import edu.chl.rocc.core.observers.IDeathListener;
import edu.chl.rocc.core.model.factories.IRoCCFactory;
import edu.chl.rocc.core.model.m2phyInterfaces.IBullet;
import edu.chl.rocc.core.model.m2phyInterfaces.ICharacter;
import edu.chl.rocc.core.model.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.m2phyInterfaces.IWeapon;
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
            /*
        if (this.frontCharacterIndex < characters.size()){
            characters.get(this.frontCharacterIndex).move(dir);
            characters.get(this.frontCharacterIndex).setCurrentDirection(dir);
            */
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
    public void removeLead() {
        characters.get(activeCharacterIndex).dispose();
        if (characters.size() > 1) {
            int playerToRemove = activeCharacterIndex;
            this.cycleActivePlayer();
            characters.remove(playerToRemove);
            activeCharacterIndex = activeWeaponIndex % characters.size();
        } else {
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
    public IWeapon getWeapon(){
        return this.weapons.get(activeWeaponIndex);
    }

    @Override
    public List<IWeapon> getWeapons(){
        return this.weapons;
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

    @Override
    public void cycleActivePlayer() {
        this.setActiveCharacter((activeCharacterIndex + 1) % characters.size());
    }

    @Override
    public int getFrontCharacterIndex(){
        return activeCharacterIndex;
    }

    @Override
    public float getDistance(int i){
        return Math.abs(characters.get(i).getX() - characters.get(activeCharacterIndex).getX());
    }

    @Override
    public void addToScore(int value){
        this.score +=value;
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
