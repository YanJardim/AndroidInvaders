package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.provider.Settings;

/**
 * Created by developer on 15/05/17.
 */

public class Enemy extends AnimatedGameObject {

    private Matrix matrix = new Matrix();
    private Vector2 rangeIndex;
    private int maxIndex, currentIndex;
    private float timer, maxTime;
    public boolean rightDir;
    private float speed, speedY;

    public Enemy(String filename, AssetManager assetManager,int framesW,int framesH, float x, float y){
        loadAnimation(filename, assetManager, framesW, framesH);
        this.x = x;
        this.y = y;
        rangeIndex = new Vector2(-3, 20);
        currentIndex = 0;
        maxIndex = 3;
        timer = 0;
        maxTime = 0.1f;
        rightDir = true;
        scaleAllFrames(100, true);
        speed = 2;
        speedY = 10;

    }
    @Override
    public void update(float deltaTime){
        //System.out.println(width + " - " + height);
        //enemyMovement(deltaTime);
    }

    public void moveHorizontal(float deltaTime){
        if(rightDir) x+=speed * deltaTime;
        else x-=speed * deltaTime;
    }

    public void moveVertical(float deltaTime){
        y += speedY * deltaTime;
    }

    public void enemyMovement(float deltaTime){
        timer += deltaTime / 1000;

        if(timer >= maxTime){
            //System.out.println(currentIndex);

            if(currentIndex <= rangeIndex.getX()){
                rightDir = false;
                y += speedY * deltaTime;

            }
            else if(currentIndex >= rangeIndex.getY()){
                rightDir = true;
                y +=speedY * deltaTime;
            }
            if(rightDir){
                currentIndex --;
                x -= speed * deltaTime;

            }
            else{
                currentIndex ++;
                x+=speed * deltaTime;

            }

            timer = 0;
        }
    }

    public void swapDirection(){
        if(rightDir) rightDir = false;
        else if(!rightDir) rightDir = true;
    }

    public boolean checkHit(Rect target){
        Rect r = new Rect((int)getX(), (int)getY(), getWidth(), getHeight());
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
