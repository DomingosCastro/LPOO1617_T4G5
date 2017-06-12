package com.mygdx.bunnygame.Controller;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.Model.Entities.Bunny;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.View.GameView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class GameController
 */
public class GameController {

    /**
     * The singleton instance of this controller
     */
    private static GameController instance;

    private final World world;
    private GameModel gameModel;
    private static GameView gameView;
    private Bunny bunny;

    /**
     * GameController Class constructor
     * @param gameView
     */
    private GameController( GameView gameView) {

        this.gameView=gameView;
        this.world = new World(new Vector2(0, -12), true);
        world.setContactListener(new WorldContactListener());
        gameModel = gameModel.getInstance(world, gameView.getMap());
        this.bunny = gameModel.getBunny();
    }

    /**
     * Function getInstance - returns an instance of game controller
     * @param gameView
     * @return
     */
    public static GameController getInstance(GameView gameView) {
        if (instance == null)
            instance = new GameController( gameView );
        return instance;
    }

    /**
     * Function update - update of gameModel
     * @param dt
     */
    public void update(float dt){
        gameModel.update(dt);
    }

    /**
     * Function getWorld - returns the game world
     * @return
     */
    public World getWorld(){
        return world;
    }

    /**
     * Function inputAction - Pressing the key while in the air changes color
     */
    public void inputAction(){
            if (bunny.jumpAvailable())
                bunny.jump(6f);
            else if (bunny.colorAvailable())
                bunny.changeColor();
        }
    }


