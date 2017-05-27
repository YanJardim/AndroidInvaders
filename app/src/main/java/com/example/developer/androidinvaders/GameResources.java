package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 03/04/17.
 */
public class GameResources {
    private static GameResources ourInstance = new GameResources();

    public static GameResources getInstance() {
        if(ourInstance == null){
            ourInstance = new GameResources();
        }

        return ourInstance;
    }

    List<GameObject> gameObjectList = new ArrayList<GameObject>();


    private GameResources() {

    }

    public void addObject(GameObject obj){
        gameObjectList.add(obj);

    }

    public void removeObject(GameObject obj){
        gameObjectList.remove(obj);
    }

    public void updateAndDraw(float deltaTime, Canvas canvas, Paint paint){
        for(GameObject obj : gameObjectList){
            obj.update(deltaTime);
            obj.draw(canvas, paint);
        }
    }


}
