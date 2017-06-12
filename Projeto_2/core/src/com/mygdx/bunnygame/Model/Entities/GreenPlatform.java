package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.Model.GameModel;

public class GreenPlatform extends Platform {
    public GreenPlatform(World world, Rectangle bounds) {
        super(world, bounds);
        setCategoryFilter(GameModel.GREENPLATFORM_BIT);

    }
}
