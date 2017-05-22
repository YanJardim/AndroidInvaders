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

    public EnemiesController(Context context, View view){
        enemies = new ArrayList<Enemy>();
        this.context = context;
        this.view = view;
        padding = new Vector2(100, 5);
        enemySize = new Vector2(100, 120);
    }


    public void initEnemies(){
        float totalW = (view.getWidth() / 2) / enemySize.getX();
        float totalH = view.getHeight() / enemySize.getY();
        
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 5; j++) {
                //enemies.add(new Enemy("enemy" + (j + 2) + ".png", context.getAssets(), 2, 1, i * 100 + padding.getX(), j * 100 + padding.getY()));
                enemies.add(new Enemy("Sprites/enemy" + (j + 2) + ".png", context.getAssets(), 2, 1, ((i * enemySize.getX()) + (i * padding.getX())) , (j * enemySize.getY()) + (i * padding.getY())));
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

}
