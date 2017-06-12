package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;

public abstract class Platform {
    protected World world;
    protected Rectangle bounds;
    protected Body body;
    protected Fixture fixture;

    public Platform(World world, Rectangle bounds) {

        this.world = world;
        this.bounds = bounds;

        BodyDef bdef = new BodyDef();
        FixtureDef fdef = new FixtureDef();
        PolygonShape shape = new PolygonShape();


        bdef.type = BodyDef.BodyType.StaticBody;
        bdef.position.set((bounds.getX() + bounds.getWidth() / 2) / BunnyGameMain.PPM, (bounds.getY() + bounds.getHeight() / 2) / BunnyGameMain.PPM);

        body = world.createBody(bdef);

        shape.setAsBox(bounds.getWidth() / 2 / BunnyGameMain.PPM, bounds.getHeight() / 2 / BunnyGameMain.PPM);
        fdef.shape = shape;
        fdef.friction = 0;

        fixture = body.createFixture(fdef);

        fixture.setUserData(this);
    }

    public void setCategoryFilter(short filterBit) {
        Filter filter = new Filter();
        filter.categoryBits = filterBit;
        fixture.setFilterData(filter);
    }

}