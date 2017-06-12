package com.mygdx.bunnygame.Model;


import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Entities.Clock;
import com.mygdx.bunnygame.Model.Entities.Coin;
import com.mygdx.bunnygame.Model.Entities.Crawler;
import com.mygdx.bunnygame.Model.Entities.Enemy;
import com.mygdx.bunnygame.Model.Entities.GreenPlatform;
import com.mygdx.bunnygame.Model.Entities.Ground;
import com.mygdx.bunnygame.Model.Entities.InvisibleWall;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.Entities.Life;
import com.mygdx.bunnygame.Model.Entities.YellowPlatform;

import java.util.ArrayList;

/**
 * Class B2WorldCreator
 */

public class B2WorldCreator {

    public static final int greenPlatObjectLayer = 2;
    public static final int yellowPlatObjectLayer = 3;
    public static final int coinsObjectLayer = 4;
    public static final int invisibleWallObjectLayer = 5;
    public static final int crawlerObjectLayer = 6;
    public static final int clockObjectLayer = 7;
    public static final int lifeObjectLayer = 8;
    public static final int groundObjectLayer = 9;
    public static final int protectionObjectLayer = 10;
    public static final int jumperObjectLayer = 11;

    private Array<Item> items;
    private Array<Enemy> enemies;

/*
    private Array<Crawler> crawlers;
    private Array<Jumper> jumpers;
    private Array<Clock> clocks;
    private Array<Life> lifes;

    private Array<Protection> protections;
    */

    public B2WorldCreator(World world, TiledMap map) { // PlayScreen instead of the world World and the TiledMap map

        items = new Array<Item>();
        enemies = new Array<Enemy>();
        // Ciclo que percorre as plataformas verdes armazenadas no mapa
        for (MapObject object : map.getLayers().get(greenPlatObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new GreenPlatform(world, rect);
        }

        // Ciclo que percorre as plataformas amarelas armazenadas no mapa
        for (MapObject object : map.getLayers().get(yellowPlatObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new YellowPlatform(world, rect);
        }

        // Create Ground
        for (MapObject object : map.getLayers().get(groundObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            //new YellowPlatform(world, map, rect);
            new Ground(world, rect);
        }

        // create coin bodies/fixtures
        for (MapObject object : map.getLayers().get(coinsObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            items.add(new Coin(world, rect.getX() / BunnyGameMain.PPM, rect.getY() / BunnyGameMain.PPM));
        }

        // create Clock
        for (MapObject object : map.getLayers().get(clockObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            items.add(new Clock(world, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }

        // create Life
        for (MapObject object : map.getLayers().get(lifeObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            items.add(new Life(world, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }

        // create Invisible walls
        for (MapObject object : map.getLayers().get(invisibleWallObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            new InvisibleWall(world, rect);
        }

        // create Crawlers

        for (MapObject object : map.getLayers().get(crawlerObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            enemies.add(new Crawler(world, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }

/*

        // create Crawlers
        crawlers=new Array<Crawler>();
        for (MapObject object : map.getLayers().get(crawlerObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            crawlers.add(new Crawler(screen, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }

        // create Crawlers
        jumpers=new Array<Jumper>();
        for (MapObject object : map.getLayers().get(jumperObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            jumpers.add(new Jumper(screen, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }





        // create Protection
        protections=new Array<Protection>();
        for (MapObject object : map.getLayers().get(protectionObjectLayer).getObjects().getByType(RectangleMapObject.class)) {
            Rectangle rect = ((RectangleMapObject) object).getRectangle();
            protections.add(new Protection(screen, rect.getX()/ BunnyGameMain.PPM, rect.getY()/ BunnyGameMain.PPM ));
        }
*/
    }

    /*

    public Array<Crawler> getCrawlers() {
        return crawlers;
    }
    public Array<Jumper> getJumpers() {return jumpers;}
    public Array<Clock> getClocks(){ return clocks;}
    public Array<Life> getLifes(){return lifes;}
    public Array<Coin> getCoins(){ return coins;}
    public Array<Protection> getProtections(){
        return protections;
    };

    */

    public Array<Item> getItems() {
        return items;
    }
    public Array<Enemy> getEnemies() {
        return enemies;
    }
}

