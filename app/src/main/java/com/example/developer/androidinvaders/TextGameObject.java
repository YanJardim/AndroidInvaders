package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by developer on 20/03/17.
 */

public class TextGameObject extends GameObject
{
    private int color;
    private int size = 10;
    private String text = "";
    private Rect bounds;
    public TextGameObject(String text, int color, int size, Vector2 position){
        this.setText(text);
        this.setColor(color);
        this.setSize(size);
        setX(position.getX());
        setY(position.getY());
        bounds = new Rect();
    }

    @Override
    public void update(float deltaTime) {

    }

    @Override
    public void draw(Canvas canvas, Paint paint)
    {
        paint.setColor(getColor());
        paint.setTextSize(getSize());
        paint.getTextBounds(getText(), 0, getText().length(), bounds);

        canvas.drawText(getText(), getX(), getY(),paint);
    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Rect getBounds() {
        return bounds;
    }

    public void setBounds(Rect bounds) {
        this.bounds = bounds;
    }

    public Vector2 getCenter(Canvas canvas, Paint paint){
        Rect r = new Rect();
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;

        return new Vector2(x, y);
    }
}
