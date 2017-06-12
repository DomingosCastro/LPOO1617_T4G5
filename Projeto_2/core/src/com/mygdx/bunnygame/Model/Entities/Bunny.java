package com.mygdx.bunnygame.Model.Entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Filter;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.bunnygame.BunnyGameMain;
import com.mygdx.bunnygame.Model.GameModel;
import com.mygdx.bunnygame.JumpState;
import com.mygdx.bunnygame.PowerState;

/**
 * Class Bunny - extends EntityBody
 *  It stores the jump state, power state, velocity , color and other characteristics of Bunny
 */
public class Bunny extends EntityBody {

    private JumpState jumpState;
    private PowerState powerState;
    private boolean isBosted;
    private double maxVelocity;
    private int color = 1;
    private static int shapeRadius = 15;
    public boolean bunnyDead = false;

    /**
     * Bunny Constructor - initializes Bunny class attributes;
     * @param world
     * @param x
     * @param y
     */
    public Bunny(World world, float x, float y) {
        super(world, x, y);
        jumpState=JumpState.RUNNING;
        powerState = PowerState.NORMAL;
        isBosted=false;
        maxVelocity=1.5f;
    }

    /**
     * Function defineBody
     * @param x
     * @param y
     */
    @Override
    public void defineBody(float x, float y) {
        BodyDef bdef = new BodyDef();
        bdef.position.set(x/ BunnyGameMain.PPM, y/ BunnyGameMain.PPM);
        bdef.type=BodyDef.BodyType.DynamicBody; // define o corpo como dynamic: sujeito a forças do world como gravidade

        b2body = world.createBody(bdef); // cria o corpo body no mundo com as definições de bdef

        FixtureDef fdef = new FixtureDef();
        CircleShape shape = new CircleShape();
        shape.setRadius(shapeRadius/ BunnyGameMain.PPM);

        fdef.filter.categoryBits = GameModel.BUNNY_BIT;

        // Define os objetos com os quais pode colidir
        fdef.filter.maskBits = GameModel.ENEMY_BIT |
                GameModel.YELLOWPLATFORM_BIT |
                GameModel.ENEMY_HEAD_BIT |
                GameModel.ITEM_BIT |
                GameModel.GROUND_BIT ;

        fdef.shape = shape;
        b2body.createFixture(fdef).setUserData(this);

        // Sensor de colisões, no corpo: DIFERENTE
        //   FixtureDef fdef = new FixtureDef();
        CircleShape bodySensor = new CircleShape();
        bodySensor.setRadius(shapeRadius/ BunnyGameMain.PPM);
        fdef.shape = bodySensor;
        fdef.isSensor = true;

        b2body.createFixture(fdef).setUserData(this);
    }

    /**
     * Function update
     * @param dt
     */
    @Override
    public void update(float dt) {
        if(!bunnyDead) {
            moveRight();
            if (b2body.getLinearVelocity().x >= 2) {
                b2body.setLinearVelocity(2, b2body.getLinearVelocity().y);
            }
        }
        else b2body.setLinearVelocity(new Vector2(0,0));
    }


    /**
     * Function jump
     * @param impulse
     */
    public void jump(float impulse){
        // Parametro 1: vetor de impulso vertical; Parametro 2: aplica o impulso no centro do corpo; Parametro 3: "acorda" o body para fazer os cálculos (o world só opera nos objetos "acordados")
        b2body.applyLinearImpulse(new Vector2(0, impulse), b2body.getWorldCenter(), true);

        jumpState = JumpState.JUMPING;
    }

    /**
     * Function moveRight
     */
    public void moveRight(){

        if (b2body.getLinearVelocity().x<=maxVelocity)
            b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), false);
    }

    /**
     * Function jumpAvailable
     * @return
     */
    public boolean jumpAvailable(){
        if (jumpState== JumpState.RUNNING && !bunnyDead)
            return true;
        else return false;
    }

    /**
     * Function colorAvailable
     * @return
     */
    public boolean colorAvailable(){
        if (jumpState == JumpState.JUMPING && !bunnyDead)
            return true;
        else return false;
    }

    /**
     * Function setPowerState
     * @param state
     */
    public void setPowerState(PowerState state){powerState=state;}

    /**
     * Function onPlatformHit
     */
    public void onPlatformHit(){
        jumpState= JumpState.RUNNING;
    }

    /**
     * Function changeColor - changes the color of Bunny
     */
    public void changeColor(){
            if (color==1) changeToGreen();
        else changeToYellow();
    }

    /**
     * Function changeToGreen - changes the color of Bunny to green
     */
    public void changeToGreen(){
        color=2;
        Filter filter = b2body.getFixtureList().first().getFilterData();
        short bits = filter.maskBits;
        bits &= ~GameModel.YELLOWPLATFORM_BIT;
        bits |= GameModel.GREENPLATFORM_BIT;
        filter.maskBits = bits;
        b2body.getFixtureList().first().setFilterData(filter);

    }

    /**
     * Function changeToYellow - changes the color of Bunny to yellow
     */
    public void changeToYellow(){
        color=1;
        Filter filter = b2body.getFixtureList().first().getFilterData();
        short bits = filter.maskBits;
        bits &= ~GameModel.GREENPLATFORM_BIT;
        bits |= GameModel.YELLOWPLATFORM_BIT;
        filter.maskBits = bits;
        b2body.getFixtureList().first().setFilterData(filter);

    }

    /**
     * Function boost
     */
    public void boost(){
        isBosted=true;
        powerState=PowerState.BOOSTED;

        maxVelocity=1.8f;
    }


    public void unboost(){
        isBosted=false;
        powerState = PowerState.NORMAL;

        maxVelocity=1.5f;
    }

    public boolean getIsBoosted() {return isBosted;}


    public PowerState getPowerState(){ return powerState;}

    public void killBunny(){
        bunnyDead = true;

    }

    public int getColor(){
        return color;
    }

}
