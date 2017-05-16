package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by developer on 20/03/17.
 */

public abstract class AnimatedGameObject extends GameObject
{
    Bitmap anims[];

    private int frames;
    private int currentFrame = 0;
    private float changeFrameTime = 1000;
    private int currentFrameWidth;
    private int currentFrameHeight;
    public void loadAnimation(String filename, AssetManager assetManager,int framesW,int framesH)
    {
        try
        {
            InputStream inputStream = assetManager.open(filename);

            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);

            inputStream.close();
            frames = framesW * framesH;

            anims = new Bitmap[frames];
            int width = bitmap.getWidth()/framesW;
            int height = bitmap.getHeight()/framesH;

            currentFrameHeight = height;
            currentFrameWidth = width;

            int index = 0;

            for(int w=0; w<framesW; w++)
            {
                for(int h=0; h<framesH; h++)
                {
                    anims[index++] = Bitmap.createBitmap(bitmap, w*width,h*height,width,height);
                }
            }




        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    float startTime =0;

    @Override
    public abstract void update(float deltaTime);

    @Override
    public void draw(Canvas canvas, Paint paint) {
        float elapsedTime = (System.nanoTime()-startTime) / 1000000;

        if(elapsedTime > changeFrameTime)
        {
            startTime = System.nanoTime();

            currentFrame++;

            if(currentFrame >= frames)
            {
                currentFrame = 0;
            }
        }

        canvas.drawBitmap(anims[currentFrame], getX(), getY(),paint);
    }
}
