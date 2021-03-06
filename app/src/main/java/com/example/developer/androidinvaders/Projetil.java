package com.example.developer.androidinvaders;

import android.content.res.AssetManager;
import android.view.View;

import java.util.List;

/**
 * Created by developer on 22/05/17.
 */

public class Projetil extends ImageGameObject
{
    public final float speed = 1;
    private View view;

    public Projetil(String file, AssetManager manager, View view, String tag)
    {
        this.view = view;
        loadImage(file,manager);
        setTag(tag);
    }

    @Override
    public void update(float deltaTime)
    {
        y -= speed * deltaTime;
    }


}
