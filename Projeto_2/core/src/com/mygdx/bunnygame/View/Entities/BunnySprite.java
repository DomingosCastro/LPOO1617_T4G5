package com.mygdx.bunnygame.View.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.EntityBody;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.PowerState;

/**
 * Created by Domingos on 10/06/2017.
 */

public class BunnySprite extends EntitySprite {

    private int spriteWidth=20;
    private int spriteHeight=30;
    private int spriteWidthPowered = 29;
    private int spriteHeightPowered = 39;

    private TextureRegion yellowBunny;
    private TextureRegion greenBunny;
    private TextureRegion yellowBunnyAttack;
    private TextureRegion greenBunnyAttack;
    private TextureRegion redBunnyAttack;
    private TextureRegion deadBunnyAttack;


    public BunnySprite(BunnyGameMain game) {
        super(game);
    }

    @Override
    public void updateTexture(GameModel gameModel, EntityBody body) {

        if (gameModel.getBunny().getPowerState()== PowerState.NORMAL) {
            setBounds(0, 0, spriteWidth / BunnyGameMain.PPM, spriteHeight / BunnyGameMain.PPM);
            if (gameModel.getBunny().getColor() == 1)
                setRegion(yellowBunny);
            else
                setRegion(greenBunny);
        }

        /*
        else if(powerState == PowerState.HURT){
            setBounds(0, 0, spriteWidth / BunnyGame.PPM, spriteHeight / BunnyGame.PPM);
            setRegion(new TextureRegion(screen.getAtlas().findRegion("coelho vermelho"), 0, 0, spriteWidth, spriteHeight));
        }
        else if (powerState == PowerState.BOOSTED){
            setBounds(0, 0, spriteWidthPowered / BunnyGame.PPM, spriteHeightPowered / BunnyGame.PPM);
            if (color == 1)
                setRegion(new TextureRegion(screen.getAtlas().findRegion("attack amarelo"), 0, 0, spriteWidthPowered, spriteHeightPowered ));
            else
                setRegion(new TextureRegion(screen.getAtlas().findRegion("attack azul"), 0, 0, spriteWidthPowered, spriteHeightPowered ));
        }
        else if (powerState == PowerState.DEAD)
            setRegion(new TextureRegion(screen.getAtlas().findRegion("coelho morto"), 0, 0, spriteWidth, spriteHeight));
        */

    }


    @Override
    public void createTextures(BunnyGameMain game) {

        Texture yellowBunnyTexture = game.getAssetManager().get("coelho amarelo.png");
        Texture greenBunnyTexture = game.getAssetManager().get("coelho azul.png");
     /* Texture yellowBunnyAttackTexture = game.getAssetManager().get("attack amarelo.png");
        Texture greenBunnyAttackTexture = game.getAssetManager().get("attack azul.png");
        Texture BunnyRed = game.getAssetManager().get("coelho vermelho.png");
        Texture BunnyDead = game.getAssetManager().get("coelho morto.png");
     */
      //  yellowBunny = new TextureRegion(yellowBunnyTexture, yellowBunnyTexture.getWidth(), yellowBunnyTexture.getHeight());


         yellowBunny=new TextureRegion(yellowBunnyTexture, yellowBunnyTexture.getWidth(), yellowBunnyTexture.getHeight());
         greenBunny=new TextureRegion(greenBunnyTexture, greenBunnyTexture.getWidth(), greenBunnyTexture.getHeight());

    }

    @Override
    public void update(EntityBody body) {

            setPosition(body.b2body.getPosition().x - getWidth()/2, body.b2body.getPosition().y - getHeight()/2);

    }

}