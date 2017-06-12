package com.mygdx.bunnygame.Model;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bunnygame.Model.Entities.Bunny;
import com.mygdx.bunnygame.Model.Entities.Enemy;
import com.mygdx.bunnygame.Model.Entities.Item;

/**
 * Created by Domingos on 10/06/2017.
 */

public class GameModel {

    // Box2D Collision Bits
    public static final short NOTHING_BIT = 0;
    public static final short GROUND_BIT = 1; // em substituição do DEFAULT_BIT
    public static final short BUNNY_BIT = 2;
    public static final short GREENPLATFORM_BIT = 16;
    public static final short YELLOWPLATFORM_BIT = 32;
    public static final short ENEMY_BIT = 64;
    public static final short ENEMY_HEAD_BIT = 128;
    public static final short INVISIBLE_WALL = 256;
    public static final short ITEM_BIT = 512;


    private static GameModel instance;
    private Hud hud;
    private Bunny bunny;
    private B2WorldCreator creator;
    private Array<Item> items;
    private Array<Enemy> enemies;
    /**
     * @return the Singleton instance
     */

    public static GameModel getInstance(World world, TiledMap map) {
        if (instance == null)
            instance = new GameModel(world, map);
        return instance;
    }

    private GameModel(World world, TiledMap map) {

        this.bunny = new Bunny(world, 100, 200);
        creator = new B2WorldCreator(world, map);
        items = creator.getItems();
        enemies = creator.getEnemies();
        hud = new Hud();
    }


    public void update(float dt) {

        bunny.update(dt);
        hud.update(dt);
        for (Item item : items)
            item.update(dt);
        for (Enemy enemy : enemies)
            enemy.update(dt);
    }

    public Bunny getBunny() {
        return bunny;
    }

    public Array<Item> getItems() {
        return items;
    }
    public Array<Enemy> getEnemies() { return enemies;}

    public float getBunnyX() {
        return bunny.b2body.getPosition().x;
    }


    public Hud getHud(){
        return hud;
    }
}
