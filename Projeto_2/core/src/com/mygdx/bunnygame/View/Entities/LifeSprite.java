package com.mygdx.bunnygame.View.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.EntityBody;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.GameModel;

/**
 * Created by Domingos on 11/06/2017.
 */

public class LifeSprite extends ItemSprite {

    private TextureRegion lifeRegion ;
    protected int spriteHeight = 19;
    protected int spriteWidth = 19;

    public LifeSprite(BunnyGameMain game, Item itemBody) {
        super(game, itemBody);
        Texture lifeTexture = game.getAssetManager().get("life.png");

        lifeRegion=new TextureRegion(lifeTexture, lifeTexture.getWidth(), lifeTexture.getHeight());

        setBounds(getX(), getY(), spriteWidth/BunnyGameMain.PPM, spriteHeight/BunnyGameMain.PPM);
        setRegion(lifeRegion);
    }

    @Override
    public void updateTexture(GameModel gameModel, EntityBody body) {
        super.updateTexture(gameModel, body);
    }

    @Override
    public void createTextures(BunnyGameMain game) {
        super.createTextures(game);
    }


}


