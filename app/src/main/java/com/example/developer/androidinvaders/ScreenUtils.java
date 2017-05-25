package com.example.developer.androidinvaders;

/**
 * Created by yan.jardim on 24/05/2017.
 */

public class ScreenUtils {

    public static float getScaleRelativeByScreen(float width, float height, float percent){
        Vector2 size = new Vector2(width * percent, height * percent);
        return Math.min(size.getX(), size.getY());
    }

}
