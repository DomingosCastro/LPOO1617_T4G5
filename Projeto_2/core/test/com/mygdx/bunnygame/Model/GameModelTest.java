package com.mygdx.bunnygame.Model;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.Model.Entities.Bunny;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Domingos on 10/06/2017.
 */
public class GameModelTest {

    @Test
    public void DefineBunny() {
        World world = new World(new Vector2(0, 0), true);
        Bunny bunny = new Bunny (world, 10, 10);

        assertNotNull(bunny);
    }
}