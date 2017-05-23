package com.example.developer.androidinvaders;

public class Vector2{
    private float x, y;

    public Vector2(float x, float y){
        this.x = x;
        this.y = y;
    }

    public float getX(){
        return x;
    }
    public float getY(){
        return y;
    }
    public void setX(float x){
        this.x = x;
    }
    public void setY(float y){
        this.y = y;
    }
    public void addX(float amount){
        x += amount;
    }
    public void addY(float amount){
        y += amount;
    }
    public void subX(float amount){
        x -= amount;
    }
    public void subY(float amount){
        y -= amount;
    }

}