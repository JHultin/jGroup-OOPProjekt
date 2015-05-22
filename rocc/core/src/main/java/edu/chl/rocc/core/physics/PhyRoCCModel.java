package edu.chl.rocc.core.physics;

import static edu.chl.rocc.core.GlobalConstants.PPM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import edu.chl.rocc.core.controller.CollisionListener;
import edu.chl.rocc.core.controller.ICollisionListener;
import edu.chl.rocc.core.controller.IDeathListener;
import edu.chl.rocc.core.factories.*;
import edu.chl.rocc.core.m2phyInterfaces.*;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.model.RoCCModel;
import edu.chl.rocc.core.utility.IDeathEvent;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A class handling all physic related logic for the RoCC game.
 * A physic-wrapper for the RoCCModel-class
 *
 * Created by Joel on 2015-05-03.
 */
public class PhyRoCCModel implements IRoCCModel {

    private IRoCCFactory factory;

    private List<IBullet> bullets;

    // The model that does all non physics related logic
    private IRoCCModel model;

    // The physics world in which all physics items exist
    private World world;

    private TiledMap tMap;

    /**
     * Constructor for the model
     */
    public PhyRoCCModel(TiledMap tMap) {

        //Change cursor/crosshair
        Pixmap pm = new Pixmap(Gdx.files.internal("crosshair.png"));
        Gdx.input.setCursorImage(pm, 16, 16);
        pm.dispose();

        this.tMap = tMap;
    }

    @Override
    public void aim(int x, int y) {
        this.model.aim(x, y);
    }

    public Vec2 getAim() {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void constructWorld() {

        // Start with cearing the world, using a gravity defined in a constantsclass
        this.world = new World(new Vec2(0, PhyConstants.GRAVITY));

        // Create the factory defining what type of objects all other non-physics objects shall create
        this.factory = new PhyRoCCFactory(world);

        // Create the model handling non-physics logic
        model = new RoCCModel(factory);

        // Get the layer with information about the solid ground
        if (tMap.getLayers().get("ground") != null) {
            TiledMapTileLayer tileLayer = (TiledMapTileLayer) tMap.getLayers().get("ground");

            // Create definitions for body and fixture
            BodyDef bDef = new BodyDef();
            FixtureDef fDef = new FixtureDef();

            // Create a shape for each groundblock
            ChainShape cs = new ChainShape();
            Vec2[] v = new Vec2[5];
            float offset = PhyConstants.BLOCK_SIZE / 2 / PPM;
            v[0] = new Vec2(-offset, -offset);
            v[1] = new Vec2(-offset,  offset);
            v[2] = new Vec2( offset,  offset);
            v[3] = new Vec2( offset, -offset);
            v[4] = new Vec2(-offset, -offset);
            cs.createChain(v, 5);

            // Define the body and fixture specifications which all block share
            bDef.type = BodyType.STATIC;
            bDef.userData = "ground";

            fDef.friction = 0;
            fDef.shape = cs;
            fDef.filter.categoryBits = BitMask.BIT_GROUND;
            fDef.filter.maskBits = BitMask.BIT_BODY | BitMask.BIT_BULLET | BitMask.BIT_ENEMY | BitMask.BIT_FOLLOWER;

            // Create a tile for each block on the map
            for (int row = 0; row < tileLayer.getHeight(); row++) {
                for (int col = 0; col < tileLayer.getWidth(); col++) {
                    TiledMapTileLayer.Cell cell = tileLayer.getCell(col, row);

                    // If there is a tile at the position
                    if (cell != null && cell.getTile() != null) {

                        // Set the position for the block
                        bDef.position.set(PhyConstants.BLOCK_SIZE * (col + 0.5f) / PPM,
                                          PhyConstants.BLOCK_SIZE * (row + 0.5f) / PPM);

                        Body body = world.createBody(bDef);
                        body.createFixture(fDef);
                        // Then let the level create the block in the world
                        model.addBlock(new PhyBody(body));
                    }
                }
            }
        }

        // Continue with the layer specifying the food
        if (tMap.getLayers().get("food") != null) {
            MapLayer foodLayer = tMap.getLayers().get("food");

            // Create one food item for each on the map
            for (MapObject mapObject : foodLayer.getObjects()) {
                float x = ((Float) mapObject.getProperties().get("x")) / PPM;
                float y = ((Float) mapObject.getProperties().get("y")) / PPM;

                IFood food = new PhyFood(world, x, y);
                model.addPickupable(food);
            }
        }
        // Do the same with the pickupable characters
        if (tMap.getLayers().get("characters") != null) {
            MapLayer ipcLayer = tMap.getLayers().get("characters");

            for (MapObject mapObject : ipcLayer.getObjects()) {
                float x = ((Float) mapObject.getProperties().get("x")) / PPM;
                float y = ((Float) mapObject.getProperties().get("y")) / PPM;

                IPickupableCharacter ipc = new PhyPickupableCharacter("noEyes", world, x, y);
                model.addPickupable(ipc);
            }
        }

        if (tMap.getLayers().get("jumpPoints") != null) {
            MapLayer jumpLayer = tMap.getLayers().get("jumpPoints");
            for(MapObject point : jumpLayer.getObjects()){
                Direction dir;
                dir = ("right".equals((String)(point.getProperties().get("direction"))) ? Direction.RIGHT : Direction.LEFT);

                float x = ((Float) point.getProperties().get("x")) / PPM;
                float y = ((Float) point.getProperties().get("y")) / PPM;

                IJumpPoint ijp = new PhyJumpPoint(world, dir , x, y);
                model.addJumpPoint(ijp);
            }
        }

        if (tMap.getLayers().get("finish") != null) {
            MapLayer finLayer = tMap.getLayers().get("finish");
            for (MapObject finish : finLayer.getObjects()) {
                float x = ((Float) finish.getProperties().get("x")) / PPM;
                float y = ((Float) finish.getProperties().get("y")) / PPM;

                float width = ((Float) finish.getProperties().get("width")) / PPM;
                float height = ((Float) finish.getProperties().get("height")) / PPM;

                IFinishPoint finPoint = new PhyFinishPoint(world, x, y, width, height);
                model.addFinish(finPoint);
            }
        }
        if (tMap.getLayers().get("enemy") != null) {
            int i = 0;
            //Add enemy in the world
            List<String> enemiesName = new ArrayList<String>();
            enemiesName.add("zombie");
            enemiesName.add("shooterEnemy");

            MapLayer enemyLayer = tMap.getLayers().get("enemy");

            for (MapObject mapObject : enemyLayer.getObjects()) {
                float x = ((Float) mapObject.getProperties().get("x")) / PPM;
                float y = ((Float) mapObject.getProperties().get("y")) / PPM;

                if(i >= enemiesName.size()){
                    i=0;
                }

                IEnemy enemy = new PhyEnemy(this.world, x, y, enemiesName.get(i));
                i++;
                model.addEnemy(enemy);
            }
        }
    }

    @Override
    public void addBlock(IBody body) {
        this.model.addBlock(body);
    }

    @Override
    public void moveSideways(Direction dir) {
        this.model.moveSideways(dir);
    }

    @Override
    public void moveFollowers(Direction dir) {
        this.model.moveFollowers(dir);
    }

    @Override
    public boolean characterIsMoving(ICharacter character){
        return false;
    }

    @Override
    public void jump() {
        this.model.jump();
    }

    @Override
    public void jumpFollowerIfPossible(){
        this.model.jumpFollowerIfPossible();
    }

    @Override
    public void shoot(float x, float y){
        this.model.shoot(x, y);
    }

    @Override
    public float getCharacterXPos() {
        return this.model.getCharacterXPos();
    }

    @Override
    public float getCharacterYPos() {
        return this.model.getCharacterYPos();
    }

    public IRoCCModel getModel() {
        return model;
    }

    @Override
    public void updateWorld(float dt) {
        this.model.updateWorld(dt);
    }

    @Override
    public List<IPickupable> getPickupables() {
        return model.getPickupables();
    }

    @Override
    public void addPickupable(IPickupable pickup) {
        this.model.addPickupable(pickup);
    }

    /**
     * Removes items from the world by using their destroy method and
     * If a character is removed, add it to the list of playable characters.
     * @param itemsToRemove list of items that wil be removed
     */
    @Override
    public void removeItems(List<IPickupable> itemsToRemove) {
        for (IPickupable pickup : itemsToRemove){
            if(pickup instanceof IPickupableCharacter){
                this.addCharacter(pickup.getName());
            }
            pickup.destroy();
        }
        model.removeItems(itemsToRemove);
    }

    @Override
    public void changeDirectionOnEnemies(List<IEnemy> enemyDirToChange){
        for (IEnemy enemy : enemyDirToChange){
            if(enemy instanceof IEnemy){
                enemy.changeMoveDirection();
            }
        }
    }

    @Override
    public void removeBullets(List<IBullet> bulletsToRemove){
        for (IBullet bullet : bulletsToRemove){
            if(bullet instanceof IBullet){
                bullet.dispose();
            }
        }
        model.removeBullets(bulletsToRemove);
    }

    @Override
    public void addJumpPoint(IJumpPoint jumpPoint) {
        this.model.addJumpPoint(jumpPoint);
    }

    @Override
    public void addFinish(IFinishPoint finish) {
        this.model.addFinish(finish);
    }

    @Override
    public void setActiveCharacter(int activeIndex) {
        this.model.setActiveCharacter(activeIndex);
    }

    @Override
    public void addWeapon(String name){
        this.model.addWeapon(name);
    }

    @Override
    public IWeapon getWeapon(){
        return this.model.getWeapon();
    }

    @Override
    public List<IBullet> getBullets() {
        return this.model.getBullets();
    }

    /*@Override
    public void createBullet(){
        this.model.createBullet();
    }*/

    @Override
    public List<ICharacter> getCharacters() {
        return this.model.getCharacters();
    }

    @Override
    public List<IEnemy> getEnemies () {
          return this.model.getEnemies();
    }

    @Override
    public void addEnemy (IEnemy enemy){
        this.model.addEnemy(enemy);
    }

    @Override
    public void setCollisionListener(ICollisionListener collisionListener) {
        this.world.setContactListener((ContactListener)collisionListener);
    }

    @Override
    public void addCharacter(String name) {
        this.model.addCharacter(name);
    }

    @Override
    public void addCharacter(String name, IDeathListener listener) {
        this.model.addCharacter(name, listener);
    }

    @Override
    public void changeLead() {
        this.model.changeLead();
    }

    @Override
    public void dispose() {
        if (model != null)
            model.dispose();
    }

    @Override
    public int getScore(){
        return model.getScore();
    }

    @Override
    public int getTime(){
        return model.getTime();
    }

    @Override
    public void handleDeath(IDeathEvent deathEvent) {
        this.model.handleDeath(deathEvent);
    }
}

