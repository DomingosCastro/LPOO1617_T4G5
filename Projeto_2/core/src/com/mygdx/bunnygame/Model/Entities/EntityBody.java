package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.World;

public abstract class EntityBody {

    public Body b2body;
    public World world;

    EntityBody(World world, float x, float y){
        this.world = world;
        defineBody(x, y);
    }

    public abstract void defineBody(float x, float y);
    public abstract void update(float dt);

    public float getX(){
        return b2body.getPosition().x;
    }

    public float getY(){
        return b2body.getPosition().y;
    }

}
