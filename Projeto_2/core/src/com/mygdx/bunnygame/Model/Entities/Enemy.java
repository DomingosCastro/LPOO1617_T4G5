package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.GameModel;

public abstract class Enemy extends EntityBody {

    protected Vector2 velocity;
    protected static int shapeRadius = 10;
    protected boolean setToDestroy;
    protected boolean destroyed;

    public Enemy(World world, float x, float y) {
        super(world, x, y);
        setToDestroy=false;
        destroyed=false;
    }

    @Override
    public void defineBody(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);

        bdef.type=BodyDef.BodyType.DynamicBody; // define o corpo como dynamic: sujeito a forças do world como gravidade

        b2body = world.createBody(bdef); // cria o corpo body no mundo com as definições de bdef

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(shapeRadius/BunnyGameMain.PPM);

        fdef.filter.categoryBits = GameModel.ENEMY_BIT;

        // Define os objetos com os quais pode colidir
        fdef.filter.maskBits = GameModel.ENEMY_BIT |
                GameModel.YELLOWPLATFORM_BIT |
                GameModel.GREENPLATFORM_BIT|
                GameModel.ENEMY_HEAD_BIT |
                GameModel.BUNNY_BIT|
                GameModel.INVISIBLE_WALL;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);


        // Create Head:
        PolygonShape head = new PolygonShape();
        Vector2[] vertice  = new Vector2[4];
        vertice[0] = new Vector2(-7, shapeRadius+4).scl(1/ BunnyGameMain.PPM);
        vertice[1] = new Vector2(7, shapeRadius+4).scl(1/BunnyGameMain.PPM);
        vertice[2] = new Vector2(-2, shapeRadius+2).scl(1/BunnyGameMain.PPM);
        vertice[3] = new Vector2(2, shapeRadius+2).scl(1/BunnyGameMain.PPM);
        head.set(vertice);

        fdef.shape = head;
        //    fdef.restitution = 0.3f;
        fdef.filter.categoryBits= GameModel.ENEMY_HEAD_BIT;
        fdef.filter.maskBits= GameModel.BUNNY_BIT;
        b2body.createFixture(fdef).setUserData(this);
    }

    @Override
    public abstract void update(float dt);

    public void hitOnHead(){
        setToDestroy=true;
    }

    public abstract void reverseVelocity(boolean x, boolean y);

    public void throwEnemy(){
        b2body.applyLinearImpulse(new Vector2(10, 10),b2body.getWorldCenter(), true);
        velocity=new Vector2(10, 10);

        Filter filter = b2body.getFixtureList().first().getFilterData();
        short bits = filter.maskBits;
        bits &= ~GameModel.INVISIBLE_WALL;
        filter.maskBits = bits;
        b2body.getFixtureList().first().setFilterData(filter);

    }

    public Vector2 getVelocity(){
        return velocity;
    }

    public boolean destroyedState(){
        return destroyed;
    }
}
