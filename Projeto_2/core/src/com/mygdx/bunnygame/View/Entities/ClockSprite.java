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

public class ClockSprite extends ItemSprite {

    private TextureRegion clockRegion ;
    protected int spriteHeight = 19;
    protected int spriteWidth = 19;

    public ClockSprite(BunnyGameMain game, Item itemBody) {
        super(game, itemBody);

        Texture coinTexture = game.getAssetManager().get("clock.png");

        clockRegion=new TextureRegion(coinTexture, coinTexture.getWidth(), coinTexture.getHeight());

        setBounds(getX(), getY(), spriteWidth/BunnyGameMain.PPM, spriteHeight/BunnyGameMain.PPM);
        setRegion(clockRegion);
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
