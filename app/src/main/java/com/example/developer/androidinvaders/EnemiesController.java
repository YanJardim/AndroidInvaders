package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 22/05/17.
 */

public class EnemiesController {

    public List<Enemy> enemies;
    private Context context;
    private View view;
    private Vector2 padding;
    private Vector2 enemySize;
    private float timer, changeDirTime;
    private final int offsetX = 100, offsetY = 150, changeDirOffest = 70;
    private boolean canChangeDir, changed;
    public EnemiesController(Context context, View view){
        enemies = new ArrayList<Enemy>();
        this.context = context;
        this.view = view;
        padding = new Vector2(100, 5);
        enemySize = new Vector2(100, 120);
        timer = 0;
        canChangeDir = false;
        changeDirTime = 0;
        changed = false;
    }


    public void initEnemies(){
        float totalW = (view.getWidth() / 2) / enemySize.getX();
        float totalH = view.getHeight() / enemySize.getY();

        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 5; j++) {
                //enemies.add(new Enemy("enemy" + (j + 2) + ".png", context.getAssets(), 2, 1, i * 100 + padding.getX(), j * 100 + padding.getY()));
                enemies.add(new Enemy("Sprites/enemy" + (j + 2) + ".png", context.getAssets(), 2, 1,
                        ((i * enemySize.getX()) + (i * padding.getX())) + offsetX ,
                        ((j * enemySize.getY()) + (i * padding.getY())) + offsetY));
            }
        }
        //enemies.add(new Enemy("Sprites/enemy2.png", context.getAssets(), 2, 1, 100, 200));
    }

    public void drawAndUpdate(Canvas canvas, Paint paint, float deltaTime){
        for(int i = 0; i < enemies.size(); i++){
            enemies.get(i).update(deltaTime);
            enemies.get(i).draw(canvas, paint);
        }
    }

    public void movementEnemies(float deltaTime){
        timer += deltaTime/1000;
        if(!canChangeDir)
            changeDirTime += deltaTime/1000;

        if(changeDirTime >= 2){
            canChangeDir = true;
            changeDirTime = 0;
        }

        if(timer >= 0.4f) {
            if(canChangeDir) {
                for (int i = 0; i < enemies.size(); i++) {
                    if (!isOnScreen(enemies.get(i))) {
                        //System.out.println("trocou");
                        swapAllDirections();
                        moveAllVertical(deltaTime);
                        canChangeDir = false;

                        break;
                    }

                }

            }

            for (int i = 0; i < enemies.size(); i++) {
                enemies.get(i).moveHorizontal(deltaTime);
                //System.out.println(enemies.get(i).getX());
            }
            timer = 0;


        }
    }

    public boolean isOnScreen(GameObject obj){
        if((obj.getX() + (obj.getWidth())) + changeDirOffest >= view.getWidth()){
            return false;
        }
        else if((obj.getX() - (obj.getWidth()))  <= 0){
            return false;
        }


        return true;
    }

    public void swapAllDirections(){
        //System.out.println(enemies.get(0).rightDir);
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).swapDirection();
        }
    }

    public void moveAllVertical(float deltaTime){
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).moveVertical(deltaTime);
        }
    }

}
