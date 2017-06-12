package com.mygdx.bunnygame.View.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.EntityBody;
import com.mygdx.bunnygame.Model.GameModel;

/**
 * Created by Domingos on 10/06/2017.
 */

public abstract class EntitySprite extends Sprite{

    //EntityBody body;
    EntitySprite(BunnyGameMain game) {

        createTextures(game);

    }

   public abstract void updateTexture(GameModel gameModel, EntityBody body);

   public abstract void createTextures(BunnyGameMain game);

   public void update(EntityBody body) {
        setPosition(body.b2body.getPosition().x - getWidth()/2, body.b2body.getPosition().y - getHeight()/2);
   }


}
