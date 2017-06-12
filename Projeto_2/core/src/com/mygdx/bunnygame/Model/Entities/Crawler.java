package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Crawler extends Enemy {
    public Crawler(World world, float x, float y) {
        super(world, x, y);
        velocity = new Vector2(-1f, b2body.getLinearVelocity().y);
    }

    @Override
    public void update(float dt) {
        if(setToDestroy && !destroyed){
            world.destroyBody(b2body);
            destroyed=true;

        }
        else if(!destroyed) {
            b2body.setLinearVelocity(velocity);
        }

    }

    @Override
    public void reverseVelocity(boolean x, boolean y) {
        if(x)
            velocity.x=-velocity.x;
        if(y)
            velocity.y=-velocity.y;
    }


}
