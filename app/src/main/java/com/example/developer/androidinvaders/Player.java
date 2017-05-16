package com.example.developer.androidinvaders;

import android.content.Context;
import android.content.res.AssetManager;
import android.view.View;

/**
 * Created by developer on 15/05/17.
 */

public class Player extends ImageGameObject
{
    public final float speed = 2;
    public  float move = 0, angle = 0;
    private View view;

    public Player(String file, AssetManager manager, View view)
    {
        this.view = view;
        loadImage(file,manager);

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
}
