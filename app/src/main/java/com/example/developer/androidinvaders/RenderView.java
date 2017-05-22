package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by developer on 03/04/17.
 */

public class RenderView extends View {
    private Paint paint = new Paint();
    private float startTime = 0;
    private Context context;
    private float timer = 0;
    private final int timeProjetilSpawn = 1000;
    private float maxTimer = timeProjetilSpawn;
    private Player player;
    public List<Projetil> projetilList = new ArrayList<>();

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

        GameResources.getInstance().addObject(new Enemy("Sprites/enemy2.png", context.getAssets(), 2, 1, 100, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy3.png", context.getAssets(), 2, 1, 300, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy4.png", context.getAssets(), 2, 1, 500, 100));
        GameResources.getInstance().addObject(new Enemy("Sprites/enemy5.png", context.getAssets(), 2, 1, 700, 100));


    }

    public void Update(float deltaTime)
    {

        if(timer >= maxTimer)
        {
            maxTimer += timeProjetilSpawn;
            createProjetil();
        }


        for(int i =0; i< projetilList.size();i++)
        {
            if(projetilList.get(i).y < 0)
            {
                GameResources.getInstance().gameObjectList.remove(projetilList.get(i));

                projetilList.remove(i);
            }


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

    public void createProjetil()
    {
        Projetil projetil = new Projetil("Sprites/Projetil.png", context.getAssets(), this);


        projetil.bitmap = projetil.scaleDown(projetil.bitmap,50,true);
        projetil.x = player.x;
        projetil.y = player.y;
        projetil.name = "Projetil";

        projetilList.add(projetil);

        GameResources.getInstance().addObject(projetil);



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
