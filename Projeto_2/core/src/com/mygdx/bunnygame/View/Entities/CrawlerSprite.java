package com.mygdx.bunnygame.View.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.Crawler;
import com.mygdx.bunnygame.Model.Entities.Enemy;
import com.mygdx.bunnygame.Model.Entities.EntityBody;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.GameModel;

/**
 * Created by Domingos on 11/06/2017.
 */

public class CrawlerSprite extends EntitySprite {

    private int spriteHeight=17;
    private int spriteWidth = 24;
 //   private TextureRegion crawlerRight;
 //   private TextureRegion crawlerLeft;
    private TextureRegion crawlerDead;

    public CrawlerSprite(BunnyGameMain game, Enemy itemBody) {

        super(game);
        Texture lifeTexture = game.getAssetManager().get("deadcrawler.png");

        crawlerDead=new TextureRegion(lifeTexture, lifeTexture.getWidth(), lifeTexture.getHeight());

        setBounds(getX(), getY(), spriteWidth/BunnyGameMain.PPM, spriteHeight/BunnyGameMain.PPM);
        setRegion(crawlerDead);
    }


    @Override
    public void updateTexture(GameModel gameModel, EntityBody body) {

    }

    @Override
    public void createTextures(BunnyGameMain game)  {

    }


}
