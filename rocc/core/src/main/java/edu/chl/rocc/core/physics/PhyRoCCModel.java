package edu.chl.rocc.core.physics;

import static edu.chl.rocc.core.GlobalConstants.PPM;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import edu.chl.rocc.core.factories.ICharacterFactory;
import edu.chl.rocc.core.factories.PhyCharacterFactory;
import edu.chl.rocc.core.factories.PhyLevelFactory;
import edu.chl.rocc.core.factories.PhyPlayerFactory;
import edu.chl.rocc.core.m2phyInterfaces.ILevel;
import edu.chl.rocc.core.m2phyInterfaces.IPlayer;
import edu.chl.rocc.core.model.Direction;
import edu.chl.rocc.core.m2phyInterfaces.IRoCCModel;
import edu.chl.rocc.core.model.RoCCModel;
import org.jbox2d.collision.shapes.ChainShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

/**
 * Created by Joel on 2015-05-03.
 */
public class PhyRoCCModel implements IRoCCModel {

    private ICharacterFactory characterFactory;

    private IRoCCModel model;
    private World world;

    public PhyRoCCModel(){
        this.world = new World(new Vec2(0, PhyConstants.GRAVITY));
        model = new RoCCModel(new PhyLevelFactory(world), new PhyPlayerFactory(world));
        characterFactory = new PhyCharacterFactory(world);

        createCharacter(200, 200);

    }

    @Override
    public void aim(int x, int y) {
        this.model.aim(x, y);
    }

    @Override
    public void constructWorld(TiledMap tMap) {
        // Get the layer with information about the solid ground
        TiledMapTileLayer tileLayer = (TiledMapTileLayer)tMap.getLayers().get("ground");

        // Create a tile for each block on the map
        for (int row = 0; row < tileLayer.getHeight(); row++){
            for (int col = 0; col < tileLayer.getWidth(); col++){
                TiledMapTileLayer.Cell cell = tileLayer.getCell(col, row);

                // If there is a tile at the position
                if (cell != null && cell.getTile() != null){

                    // Create a body definition
                    BodyDef bDef = new BodyDef();
                    bDef.type = BodyType.STATIC;
                    bDef.position.set(PhyConstants.BLOCK_SIZE * (col + 0.5f) /PPM,
                            PhyConstants.BLOCK_SIZE * (row + 0.5f) /PPM);

                    // And a fixture definition
                    ChainShape cs = new ChainShape();
                    Vec2[] v = new Vec2[5];
                    v[0] = new Vec2( -PhyConstants.BLOCK_SIZE/2/PPM, -PhyConstants.BLOCK_SIZE/2/PPM);
                    v[1] = new Vec2( -PhyConstants.BLOCK_SIZE/2/PPM,  PhyConstants.BLOCK_SIZE/2/PPM);
                    v[2] = new Vec2(  PhyConstants.BLOCK_SIZE/2/PPM,  PhyConstants.BLOCK_SIZE/2/PPM);
                    v[3] = new Vec2(  PhyConstants.BLOCK_SIZE/2/PPM, -PhyConstants.BLOCK_SIZE/2/PPM);
                    v[4] = new Vec2( -PhyConstants.BLOCK_SIZE/2/PPM, -PhyConstants.BLOCK_SIZE/2/PPM);
                    cs.createChain(v, 5);

                    FixtureDef fDef = new FixtureDef();
                    fDef.friction = 0;
                    fDef.shape = cs;
                    fDef.filter.categoryBits = BitMask.BIT_GROUND;
                    fDef.filter.maskBits = BitMask.BIT_BODY;

                    // Then let the level create the block in the world
                    model.getLevel().addBlock(bDef, fDef);
                }
            }
        }
    }

    public void createCharacter(int x, int y){
        model.getPlayer().addCharacter(characterFactory.createCharacter("", x, y));
    }

    @Override
    public void moveSideways(Direction dir) {
        this.model.moveSideways(dir);
    }

    @Override
    public void jump() {
        this.model.jump();
    }

    @Override
    public float getCharacterXPos(int i) {
        return this.model.getCharacterXPos(i);
    }

    @Override
    public float getCharacterYPos(int i) {
        return this.model.getCharacterYPos(i);
    }

    @Override
    public ILevel getLevel() {
        return this.model.getLevel();
    }

    public IPlayer getPlayer(){
        return this.model.getPlayer();
    }

    public IRoCCModel getModel(){
        return model;
    }

    @Override
    public void updateWorld(float dt) {
        this.model.updateWorld(dt);
    }
}
