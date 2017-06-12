package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.Model.Hud;

public class Coin extends Item {

    public Coin(World world, float x, float y) {
        super(world, x, y, false);

        isDynamic=false;
    }


    @Override
    public FixtureDef defineFdef() {
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius((sensorRadius/10)/ BunnyGameMain.PPM);

        fdef.filter.categoryBits= GameModel.ITEM_BIT;
        fdef.filter.maskBits = GameModel.BUNNY_BIT;

        fdef.shape = shape;

        return fdef;
    }

    @Override
    public void use(Bunny bunny) {

        destroy();
        Hud.addScore(10);

    }


}
