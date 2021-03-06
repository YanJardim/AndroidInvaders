package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.view.View;

/**
 * Created by developer on 15/05/17.
 */

public class Player extends ImageGameObject
{
    public final float speed = 0.5f;
    public  float move = 0;
    private View view;
    private boolean isDead;
    public Player(String file, AssetManager manager, View view)
    {
        this.view = view;
        loadImage(file,manager);
        setTag("Player");
        setDead(false);
    }

    @Override
    public void update(float deltaTime) {
        
        x += move * deltaTime;

        if(x > view.getWidth() - width/2)
        {
            x = view.getWidth() - width/2;
        }
        else if(x < 0 + width/2)
        {
            x = 0 + width/2;
        }
    }


    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }
}
