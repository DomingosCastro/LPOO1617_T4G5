package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;

public abstract class Item extends EntityBody{

    protected Vector2 velocity;
    protected boolean toDestroy;
    protected boolean destroyed;
    protected boolean isDynamic;
    protected int sensorRadius = 20;

    public Item(World world, float x, float y, boolean isDynamic) {
        super(world, x, y);
        this.isDynamic=isDynamic;
        defineItem(isDynamic);
        toDestroy=false;
        destroyed=false;
    }

    @Override
    public void defineBody(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x, y);
        if(isDynamic)
            bdef.type=BodyDef.BodyType.DynamicBody;
        else
            bdef.type=BodyDef.BodyType.StaticBody;

        b2body = world.createBody(bdef);

    }

    public void defineItem(boolean isDynamic) {

        BodyDef bdef = new BodyDef();
        bdef.position.set(getX(), getY());

        if(isDynamic)
            bdef.type=BodyDef.BodyType.DynamicBody; // define o corpo como dynamic: sujeito a forças do world como gravidade
        else
            bdef.type=BodyDef.BodyType.StaticBody;

        b2body = world.createBody(bdef); // cria o corpo body no mundo com as definições de bdef

        FixtureDef fdef = defineFdef();

        b2body.createFixture(fdef).setUserData(this);

        CircleShape itemSensor = new CircleShape();
        itemSensor.setRadius(sensorRadius/BunnyGameMain.PPM);
        fdef.shape = itemSensor;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }



    @Override
    public void update(float dt) {

        if(toDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed=true;
        }

        if(isDynamic){
            velocity.y=b2body.getLinearVelocity().y;
            b2body.setLinearVelocity(velocity);}
    }

    public void destroy(){
        toDestroy=true;
    }

    public abstract FixtureDef defineFdef();
    public abstract void use(Bunny bunny);

    public boolean getDestroyed(){
        return destroyed;
    }

    public void reverseVelocity(boolean x, boolean y){
        if(x)
            velocity.x=-velocity.x;
        if(y)
            velocity.y=-velocity.y;
    }
}
