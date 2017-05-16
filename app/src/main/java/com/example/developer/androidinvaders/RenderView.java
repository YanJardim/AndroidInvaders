package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.Random;

/**
 * Created by developer on 03/04/17.
 */

public class RenderView extends View {
    private Paint paint = new Paint();
    private float startTime = 0;
    private Context context;
    private float timer = 0;
    private final int increment = 500;
    private float maxTimer = increment;

    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();

        this.context = context;


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (GameResources.getInstance().gameObjectList.size() != 0) return;


        GameResources.getInstance().addObject(new Enemy("Sprites/enemy2.png", context.getAssets(), 2, 1, 100, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy3.png", context.getAssets(), 2, 1, 300, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy4.png", context.getAssets(), 2, 1, 500, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy5.png", context.getAssets(), 2, 1, 700, 100));
    }

    public void Update(float deltaTime)
    {

        if(timer >= maxTimer)
        {
            maxTimer += increment;
        }
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        float deltaTime = (System.nanoTime() - startTime) / 1000000;
        timer += deltaTime;
        canvas.drawRGB(0,0,0);

        Update(deltaTime);
        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        startTime = System.nanoTime();
        invalidate();
    }

}
