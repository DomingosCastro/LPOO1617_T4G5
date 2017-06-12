package com.mygdx.bunnygame.View;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.bunnygame.BunnyGameMain;

import static com.mygdx.bunnygame.View.GameView.V_HEIGHT;
import static com.mygdx.bunnygame.View.GameView.V_WIDTH;

/**
 * Created by Domingos on 11/06/2017.
 */

public class GameOver {


    private Viewport viewport;
    private Stage stage;
    private Game game;
    private int score;

    public GameOver(Game game) {
        this.game = game;
        viewport = new FitViewport(V_WIDTH, V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, ((BunnyGameMain) game).getBatch());

        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

        Table table = new Table();
        table.center();
        table.setFillParent(true);

        Label gameOverLabel = new Label("GAME OVER", font);

        table.add(gameOverLabel).expandX();

        stage.addActor(table);

    }



}
