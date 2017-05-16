package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
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
    private Player player;



    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();

        this.context = context;

        inputs();





    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (GameResources.getInstance().gameObjectList.size() != 0) return;
        createPlayer(getWidth()/2,getHeight()-50, 150);

    }

    public void Update(float deltaTime)
    {
        if(timer >= maxTimer)
        {

            maxTimer += increment;
        }
    }

    public void inputs()
    {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(event.getX() > getWidth()/2)
                    {
                        player.move = player.speed;
                    }
                    else
                    {
                        player.move = -player.speed;

                    }

                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    player.move = 0;
                }
                return true;
            }

        });
    }

    public void createPlayer(int x, int y, int scale)
    {
        Player spaceShip = new Player("Sprites/player.png",context.getAssets(),this);

        spaceShip.bitmap = spaceShip.scaleDown(spaceShip.bitmap,scale,true);
        spaceShip.x = x;
        spaceShip.y = y;
        spaceShip.name = "SpaceShip";
        player = spaceShip;
        GameResources.getInstance().addObject(spaceShip);

    }





    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        float deltaTime = (System.nanoTime() - startTime) / 1000000;
        timer += deltaTime;

        Update(deltaTime);

        canvas.drawRGB(0,0,0);
        startTime = System.nanoTime();
        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        invalidate();
    }

}
