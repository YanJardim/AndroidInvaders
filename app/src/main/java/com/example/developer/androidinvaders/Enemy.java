package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by developer on 15/05/17.
 */

public class Enemy extends AnimatedGameObject {

    private Matrix matrix = new Matrix();
    private Vector2 rangeIndex;
    private int maxIndex, currentIndex;
    private float timer, maxTime;
    public Enemy(String filename, AssetManager assetManager,int framesW,int framesH, float x, float y){
        loadAnimation(filename, assetManager, framesW, framesH);
        this.x = x;
        this.y = y;
        rangeIndex = new Vector2(-3, 3);
        currentIndex = 0;
        maxIndex = 3;
        timer = 0;
        maxTime = 1.5f;
    }
    @Override
    public void update(float deltaTime){
        enemyMovement();
    }

    public void enemyMovement(){
        timer += deltaTime;

        if(timer >= maxTime){
            if(currentIndex <= rangeIndex.getX()){
                currentIndex ++;
                x++;
                y++;
            }
            else if(currentIndex >= rangeIndex.getY()){
                currentIndex --;
                x--;
                y++;
            }
        }
    }
    
    public boolean checkHit(Rect target){
        Rect r = new Rect(getX(), getY(), getWidth(), getHeight());
        if(r.contains(target)){
            return true;
        }
        return false;
    }



    //@Override
    /*public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        matrix.reset();

        matrix.preTranslate(getX() - getWidth() /2, getY() - getHeight() /2);
        matrix.preRotate((float)(getAngle() *180f/(float)Math.PI), getWidth() /2f, getHeight() /2f);
        canvas.drawBitmap(bitmap, matrix, paint);
    }*/
}
