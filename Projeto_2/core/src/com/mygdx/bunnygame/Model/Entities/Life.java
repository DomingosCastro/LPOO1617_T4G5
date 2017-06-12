package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.Model.Hud;

public class Life extends Item {
    public Life(World world, float x, float y) {
        super(world, x, y, true);
        isDynamic=true;
        velocity= new Vector2(-0.1f, 0);
    }

    @Override
    public FixtureDef defineFdef() {
        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius((sensorRadius/4)/ BunnyGameMain.PPM);


        fdef.filter.categoryBits= GameModel.ITEM_BIT;
        fdef.filter.maskBits = GameModel.BUNNY_BIT |
                GameModel.GREENPLATFORM_BIT |
                GameModel.YELLOWPLATFORM_BIT|
                GameModel.INVISIBLE_WALL;

        fdef.shape = shape;

        fdef.restitution=1;

        return fdef;
    }

    @Override
    public void use(Bunny bunny) {
        destroy();
        Hud.addLife();
    }
}
