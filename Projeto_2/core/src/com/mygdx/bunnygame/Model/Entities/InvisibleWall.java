package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.Model.GameModel;

public class InvisibleWall extends Platform {
    public InvisibleWall(World world, Rectangle bounds) {
        super(world, bounds);
        setCategoryFilter(GameModel.INVISIBLE_WALL);
    }
}
