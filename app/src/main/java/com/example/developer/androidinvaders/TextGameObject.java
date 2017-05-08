package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by developer on 20/03/17.
 */

public class TextGameObject extends GameObject
{
    public int color;
    public int size = 10;
    String text = "";

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(color);
        paint.setTextSize(size);
        canvas.drawText(text,x,y,paint);
    }
}
