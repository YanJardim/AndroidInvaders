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

    public Enemy(String filename, AssetManager assetManager,int framesW,int framesH){
        loadAnimation(filename, assetManager, framesW, framesH);

    }
    @Override
    public void update(float deltaTime){

    }

    @Override
    public void draw(Canvas canvas, Paint paint){
        super.draw(canvas, paint);
        matrix.reset();

        matrix.preTranslate(getX() - getWidth() /2, getY() - getHeight() /2);
        matrix.preRotate((float)(getAngle() *180f/(float)Math.PI), getWidth() /2f, getHeight() /2f);
        canvas.drawBitmap(anims[currentFrame], matrix, paint);
    }
}
