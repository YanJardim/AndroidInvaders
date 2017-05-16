package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by developer on 03/04/17.
 */

public abstract class ImageGameObject extends GameObject {

    public Bitmap bitmap;
    Matrix matrix = new Matrix();


    public void loadImage(String filename, AssetManager manager){
        try{
            InputStream inputStream = manager.open(filename);
            bitmap = BitmapFactory.decodeStream(inputStream);
            setWidth(bitmap.getWidth());
            setHeight(bitmap.getHeight());
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    public void loadImage(String filename, AssetManager manager, int width, int height){
        try{
            InputStream inputStream = manager.open(filename);
            bitmap = BitmapFactory.decodeStream(inputStream);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height);
            this.setWidth(bitmap.getWidth());
            this.setHeight(bitmap.getHeight());
            inputStream.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public abstract void update(float deltaTime);

    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
<<<<<<< HEAD
        matrix.preTranslate(x-(width/2), y - (height /2) );
        matrix.preRotate((float)(angle*180/Math.PI), width/2, height/2);
=======
        matrix.preTranslate(getX() - getWidth() /2, getY() - getHeight() /2 );
        matrix.preRotate((float)(getAngle() *180/Math.PI), getWidth() /2, getHeight() /2);
>>>>>>> deb

        canvas.drawBitmap(bitmap, matrix, null);
    }
}
