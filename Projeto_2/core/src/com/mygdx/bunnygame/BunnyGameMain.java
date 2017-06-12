package com.mygdx.bunnygame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.bunnygame.View.GameView;
//import com.mygdx.bunnygame.View.GameView;

/**
 * Class BunnyGameMain
 */

public class BunnyGameMain extends Game {
    private SpriteBatch batch;
    private AssetManager assetManager;
    public static final float PPM = 100;

    @Override
    public void create () {
        batch = new SpriteBatch();
        assetManager = new AssetManager();

        startGame();
    }




    private void startGame() {
       setScreen(new GameView(this));
    }


    public AssetManager getAssetManager() {
        return assetManager;
    }


    @Override
    public void dispose () {
        batch.dispose();
        assetManager.dispose();
    }

    public Batch getBatch(){
        return batch;
    }

}
