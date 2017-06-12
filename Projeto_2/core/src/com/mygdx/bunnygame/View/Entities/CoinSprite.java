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


public class CoinSprite extends ItemSprite {

    private TextureRegion coinRegion ;
    protected int spriteHeight = 19;
    protected int spriteWidth = 19;

    public CoinSprite(BunnyGameMain game, Item itemBody) {
        super(game, itemBody);
        Texture coinTexture = game.getAssetManager().get("coin.png");

        coinRegion=new TextureRegion(coinTexture, coinTexture.getWidth(), coinTexture.getHeight());

        setBounds(getX(), getY(), spriteWidth/BunnyGameMain.PPM, spriteHeight/BunnyGameMain.PPM);
        setRegion(coinRegion);
    }

    @Override
    public void updateTexture(GameModel gameModel, EntityBody body) {

    }

    @Override
    public void createTextures(BunnyGameMain game) {

    }


}
