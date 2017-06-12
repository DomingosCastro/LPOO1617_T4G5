package com.mygdx.bunnygame.View.Entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.EntityBody;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.GameModel;

/**
 * Created by Domingos on 11/06/2017.
 */

public abstract class ItemSprite extends EntitySprite {

    protected EntityBody itemBody;

    public ItemSprite(BunnyGameMain game, EntityBody itemBody) {
        super(game);
        this.itemBody=itemBody;
    }

    @Override
    public void updateTexture(GameModel gameModel, EntityBody body) {

    }

    @Override
    public void createTextures(BunnyGameMain game) {

    }

}
