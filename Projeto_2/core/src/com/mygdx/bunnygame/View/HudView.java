package com.mygdx.bunnygame.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.Hud;

/**
 * Created by Domingos on 11/06/2017.
 */

public class HudView {

    public Stage stage;
    private Viewport viewport;

    private Label timeValueLabel;
    private static Label scoreValueLabel;
    private Label scoreTextLabel;
    private Label timeTextLabel;
    private static Label lifeValueLabel;
    private Label lifeTextLabel;
    private Hud hudValues;
    public HudView(SpriteBatch sb, Hud hudValues) {
        viewport = new FitViewport(GameView.V_WIDTH, GameView.V_HEIGHT, new OrthographicCamera());
        stage = new Stage(viewport, sb); // it can use the same one, it's going to recreate a new spritebatch

        Table table = new Table(); // to create the table
        table.top(); // it's going to put it at the top of our stage
        table.setFillParent(true); // our table now is the size of our stage
        this.hudValues=hudValues;
        timeValueLabel = new Label(String.format("%03d", hudValues.getTime()), new Label.LabelStyle(new BitmapFont(), Color.WHITE)); // 03: number of numbers in our countdown timer
        scoreValueLabel = new Label(String.format("%06d",hudValues.getScore()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeValueLabel = new Label(String.format("%.0f", hudValues.getLife()), new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        timeTextLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        scoreTextLabel = new Label("SCORE", new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        lifeTextLabel = new Label("LIFES", new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        table.add(scoreTextLabel).expandX().padTop(10);

        table.add(timeTextLabel).expandX().padTop(10);

        table.add(lifeTextLabel).expandX().padTop(10);

        table.row(); // everything below this will be on a new row
        table.add(scoreValueLabel).expandX(); // we don't need to pad that anymore
        table.add(timeValueLabel).expandX();
        table.add(lifeValueLabel).expandX();
        // Add our table to the stage
        stage.addActor(table);
    }

    public void updateHudView(){
        timeValueLabel.setText(String.format("%03d",hudValues.getTime())); // to turn our worldTimer into text
        scoreValueLabel.setText(String.format("%06d", hudValues.getScore()));
        lifeValueLabel.setText(String.format("%.0f", hudValues.getLife()));
    }



}
