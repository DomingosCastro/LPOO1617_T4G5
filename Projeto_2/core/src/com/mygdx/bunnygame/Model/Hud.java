package com.mygdx.bunnygame.Model;

/**
 * Class Hud
 */

public class Hud {

    private static Integer worldTimer;
    private float timeCount;
    private static Integer score;
    private static double lifeValue;
    private static Hud instance;


    public Hud(){
        worldTimer = 10;
        timeCount = 0;
        score = 0;
        lifeValue=5;
    }


    public void update(float dt) {
        timeCount += dt;
        if(timeCount >= 1) { // 1 segundo
            if(worldTimer>0)
                worldTimer--;
              timeCount = 0;
        }
    }

    public static void addScore(int value) {
        score += value/2;
    }

    public static void decreaseLife() {
        lifeValue = lifeValue - 0.5;

        if(lifeValue<=0)
            lifeValue = 0;

    }

    public static void addLife() {
        lifeValue = lifeValue + 0.5;
         }

    public static void addTime(int value) {
        worldTimer += value/2;
     }


    public double getLife(){
        return lifeValue;
    }

    public int getScore(){
        return score;
    }

    public Integer getTime(){
        return worldTimer;
    }

}
