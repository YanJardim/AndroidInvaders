package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by developer on 03/04/17.
 */

public class GameObject {
    public float x = 0, y = 0;
    public float width = 0, height = 0;
    public double angle = 0;
    public String name = "";
    public Rect getBoudingBox(){
        Rect r = new Rect((int)(x-width/2), (int)(y-height/2), (int)(x+width/2), (int)(y+height/2));
        return r;
    }
    public void update(float deltaTime){

    }
    public void draw(Canvas canvas, Paint paint){

    }

}
