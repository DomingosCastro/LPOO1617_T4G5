package com.mygdx.bunnygame.Controller;

import com.badlogic.gdx.physics.box2d.Contact;
import com.badlogic.gdx.physics.box2d.ContactImpulse;
import com.badlogic.gdx.physics.box2d.ContactListener;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.Manifold;
import com.mygdx.bunnygame.Model.Entities.Bunny;
import com.mygdx.bunnygame.Model.Entities.Enemy;
import com.mygdx.bunnygame.Model.Entities.Item;
import com.mygdx.bunnygame.Model.Entities.Platform;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.Model.Hud;

// Contact listener é chamado quando duas FIXTURES colidem

/**
 * Class WorldContactListener
 */

public class WorldContactListener implements ContactListener {

    private Hud hud;

    // chamado no inicio da colisao

    /**
     * Function beginContact - establishes contact with all game obstacles
     * @param contact
     */
    @Override
    public void beginContact(Contact contact) {

        Fixture fixA = contact.getFixtureA();
        Fixture fixB = contact.getFixtureB();
        boolean headColision = false;

        int cDef = fixA.getFilterData().categoryBits | fixB.getFilterData().categoryBits;


        if (fixA.getUserData() instanceof Bunny || fixB.getUserData() instanceof Bunny) {
            Fixture bodyShape = fixA.getUserData() instanceof Bunny ? fixA : fixB; // Fixture head é igual a fixA se fixA.getUserDate for igual a "head". Se nao, Fixture head é igual a fixB
            Fixture object = bodyShape == fixA ? fixB : fixA; // a fixA é a cabeça, entao fixB será o objeto com o qual colide. Se não, fixA será o objeto        }

            // Retorna true se o objeto for um Plataforma
            if (object.getUserData() != null && Platform.class.isAssignableFrom(object.getUserData().getClass())) {
                ((Bunny) bodyShape.getUserData()).onPlatformHit();
            }

        }


        switch (cDef) {
            case GameModel.ENEMY_HEAD_BIT | GameModel.BUNNY_BIT:
                if (fixA.getFilterData().categoryBits== GameModel.ENEMY_HEAD_BIT){
                    ((Enemy)fixA.getUserData()).hitOnHead();
          //          ((Bunny)fixB.getUserData()).jump(1.5f);
                    }
                else{
                    ((Enemy)fixB.getUserData()).hitOnHead();
           //         ((Bunny)fixA.getUserData()).jump(1.5f);
                    }
                break;

            case GameModel.ENEMY_BIT | GameModel.INVISIBLE_WALL:
                if (fixA.getFilterData().categoryBits== GameModel.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;

            case GameModel.ENEMY_BIT | GameModel.ENEMY_BIT:
                if (fixA.getFilterData().categoryBits== GameModel.ENEMY_BIT)
                    ((Enemy)fixA.getUserData()).reverseVelocity(true, false);
                    ((Enemy)fixB.getUserData()).reverseVelocity(true, false);
                break;

            case GameModel.BUNNY_BIT | GameModel.ITEM_BIT:
                if (fixA.getFilterData().categoryBits == GameModel.ITEM_BIT)
                    ((Item) fixA.getUserData()).use((Bunny) fixB.getUserData());
                else
                    ((Item) fixB.getUserData()).use((Bunny) fixA.getUserData());

                break;

            case GameModel.ITEM_BIT | GameModel.INVISIBLE_WALL:
                if (fixA.getFilterData().categoryBits== GameModel.ENEMY_BIT)
                    ((Item)fixA.getUserData()).reverseVelocity(true, false);
                else
                    ((Item)fixB.getUserData()).reverseVelocity(true, false);
                break;


            case GameModel.BUNNY_BIT | GameModel.ENEMY_BIT:
                if (fixA.getFilterData().categoryBits== GameModel.BUNNY_BIT){
                    if(((Bunny)fixA.getUserData()).getIsBoosted())
                        ((Enemy)fixB.getUserData()).throwEnemy();
                    else{
                        hud.decreaseLife();
 //                       ((Bunny)fixA.getUserData()).setPowerState(PowerState.HURT);
  //                      ((Bunny)fixA.getUserData()).changeImage();
                    }
                }
                else {
                    if (((Bunny) fixB.getUserData()).getIsBoosted())
                        ((Enemy) fixA.getUserData()).throwEnemy();
                    else{
                        hud.decreaseLife();
 //                       ((Bunny)fixB.getUserData()).setPowerState(PowerState.HURT);
  //                      ((Bunny)fixB.getUserData()).changeImage();
                    }
                }
             break;

            case GameModel.GROUND_BIT | GameModel.BUNNY_BIT:
                if (fixA.getFilterData().categoryBits== GameModel.BUNNY_BIT){
                    ((Bunny)fixA.getUserData()).killBunny();}
                else{
                    ((Bunny) fixB.getUserData()).killBunny();}
                break;

       }



    }

    /**
     * Function endContact - called in the end of collision
     * @param contact
     */
    @Override
    public void endContact(Contact contact) {

    }

    /**
     * Function preSolve
     * @param contact
     * @param oldManifold
     */
    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    /**
     * Function postSolve
     * @param contact
     * @param impulse
     */
    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

}
