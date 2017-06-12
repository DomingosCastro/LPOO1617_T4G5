package com.mygdx.bunnygame.View;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Controller.GameController;
import com.mygdx.bunnygame.Model.Entities.Bunny;
import com.mygdx.bunnygame.Model.Entities.Clock;
import com.mygdx.bunnygame.Model.Entities.Coin;
import com.mygdx.bunnygame.Model.Entities.Crawler;
import com.mygdx.bunnygame.Model.Entities.Enemy;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.Entities.Life;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.PowerState;
import com.mygdx.bunnygame.View.Entities.BunnySprite;
import com.mygdx.bunnygame.View.Entities.ClockSprite;
import com.mygdx.bunnygame.View.Entities.CoinSprite;
import com.mygdx.bunnygame.View.Entities.CrawlerSprite;
import com.mygdx.bunnygame.View.Entities.EntitySprite;
import com.mygdx.bunnygame.View.Entities.LifeSprite;


/**
 * Created by Domingos on 10/06/2017.
 */


public class GameView implements Screen {

    public static final int V_WIDTH = 500;
    public static final int V_HEIGHT = 240;

    private final BunnyGameMain game;
    private final OrthographicCamera gamecam;
    private Viewport gamePort;

    private GameController gameController;
    private GameModel gameModel;

    private Box2DDebugRenderer debugRenderer;
    private GameOver gameOver;
    private TmxMapLoader maploader; // to load our map into the game
    private TiledMap map;
    private OrthogonalTiledMapRenderer mapRenderer;
    private Box2DDebugRenderer b2dr;
    private World world;
    private HudView hudView;
    public GameView(BunnyGameMain game) {

        this.game = game;
         gamecam =  new OrthographicCamera();
        gamePort = new FitViewport(V_WIDTH / BunnyGameMain.PPM, V_HEIGHT / BunnyGameMain.PPM, gamecam);

        loadAssets();

        mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / BunnyGameMain.PPM);

        gamecam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0); // 0 for the z axis

         b2dr = new Box2DDebugRenderer();

        gameController = GameController.getInstance(this);
        world=gameController.getWorld();
        gameModel = GameModel.getInstance(gameController.getWorld(), getMap());

        hudView = new HudView((SpriteBatch) game.getBatch(), gameModel.getHud());

    }


    public void loadAssets(){
        maploader = new TmxMapLoader();
        map = maploader.load("newTiledMap2.tmx");

        this.game.getAssetManager().load( "coelho amarelo.png" , Texture.class);
        this.game.getAssetManager().load( "coelho azul.png" , Texture.class);
        this.game.getAssetManager().load( "coelho vermelho.png" , Texture.class);
        this.game.getAssetManager().load( "coelho morto.png" , Texture.class);

        this.game.getAssetManager().load( "coin.png" , Texture.class);
        this.game.getAssetManager().load( "clock.png" , Texture.class);
        this.game.getAssetManager().load( "life.png" , Texture.class);


        this.game.getAssetManager().load( "crawler.png" , Texture.class);
        this.game.getAssetManager().load( "crawlerLeft.png" , Texture.class);
        this.game.getAssetManager().load( "deadcrawler.png" , Texture.class);


        this.game.getAssetManager().finishLoading();

    }


    public TiledMap getMap(){
        return map;
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {


            update(delta);

            Gdx.gl.glClearColor(0, 0, 0, 1);
            Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

            mapRenderer.render();
            b2dr.render(gameController.getWorld(), gamecam.combined);
            game.getBatch().setProjectionMatrix(gamecam.combined);

            game.getBatch().begin();
            updateSprites();
            game.getBatch().end();

            hudView.stage.draw();

    }

    @Override
    public void resize(int width, int height) {
        gamePort.update(width, height);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    public void update(float dt){

        handleInput(dt);
        world.step(1 / 60f, 6, 2);
        gameController.update(dt);

        gamecam.position.x = gameModel.getBunnyX() + V_WIDTH / BunnyGameMain.PPM / 4;

        gamecam.update();
        mapRenderer.setView(gamecam);

        hudView.updateHudView();
  //      checkBunnyLife();
    }


    public void handleInput(float dt) {
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            gameController.inputAction();
        }
    }

    private void updateSprites() {

        Bunny bunny = gameModel.getBunny();
        EntitySprite bunnySprite = new BunnySprite(game);
        bunnySprite.updateTexture(gameModel, bunny);
        bunnySprite.update(bunny);
        bunnySprite.draw(game.getBatch());

        Array<Item> items = gameModel.getItems();
        for (Item item : items) {
            if (!item.getDestroyed()) {
                if (item instanceof Coin) {
                    EntitySprite itemSprite = new CoinSprite(game, item);
                    itemSprite.update(item);
                    try {
                        itemSprite.draw(game.getBatch());
                    } catch(NullPointerException e){
                        System.out.println("erro");
                    }
                }

                else if (item instanceof Clock) {
                    EntitySprite itemSprite = new ClockSprite(game, item);
                    itemSprite.update(item);
                    itemSprite.draw(game.getBatch());
                }

                else if (item instanceof Life) {
                    EntitySprite itemSprite = new LifeSprite(game, item);
                    itemSprite.update(item);
                    itemSprite.draw(game.getBatch());
                }
            }

        }

        Array<Enemy> enemies = gameModel.getEnemies();
        for (Enemy enemy : enemies) {
            if (!enemy.destroyedState()) {
                if (enemy instanceof Crawler) {
                    EntitySprite enemySprite = new CrawlerSprite(game, enemy);
                    enemySprite.updateTexture(gameModel, enemy);
                    enemySprite.update(enemy);
                    enemySprite.draw(game.getBatch());
                }
/*
                if (enemy instanceof Jumper) {
                    EntitySprite enemySprite = new JumperSprite(game);
                    enemySprite.update(enemy);
                    enemySprite.draw(game.getBatch());
                }
*/
            }
        }
    }
    @Override
    public void dispose() {

        map.dispose();
        mapRenderer.dispose();
        b2dr.dispose();

    }

}
