package com.example.developer.androidinvaders;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by developer on 03/04/17.
 */


public abstract class GameObject {
    protected float x = 0;
    protected float y = 0;
    protected int width = 0;
    protected int height = 0;
    protected double angle = 0;
    protected float speed = 0;
    protected String name = "";
    private String tag;


    public Rect getBoudingBox()
    {
        Rect r = new Rect((int)(getX()), (int)(getY()), (int)(getX() + getWidth()), (int)(getY() + getHeight()));

        return r;
    }

    public void drawRect(Canvas canvas, Paint paint)
    {
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(getBoudingBox(),paint);

    }

    public boolean Collision(GameObject other)
    {
        if(this.getBoudingBox().intersect(other.getBoudingBox()))
        {
            return true;
        }

        return false;
    }


    public abstract void update(float deltaTime);
    public abstract void draw(Canvas canvas, Paint paint);


    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }


    public Bitmap scale(Bitmap realImage, float maxImageSize,
                        boolean filter) {
        float ratio = Math.min(
                (float) maxImageSize / realImage.getWidth(),
                (float) maxImageSize / realImage.getHeight());
        width = Math.round((float) ratio * realImage.getWidth());
        height = Math.round((float) ratio * realImage.getHeight());

        Bitmap newBitmap = Bitmap.createScaledBitmap(realImage, width,
                height, filter);
        return newBitmap;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
