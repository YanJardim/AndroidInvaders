package com.example.developer.androidinvaders;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by yan on 27/05/2017.
 */

public class ScoreManager {
    private static ScoreManager ourInstance = new ScoreManager();

    public static ScoreManager getInstance() {
        if(ourInstance == null){
            ourInstance = new ScoreManager();
        }

        return ourInstance;
    }

    private int score;
    private final String path = "Score.ani";
    public void saveScore(Context context, int score){
        this.score = score;
        System.out.println(score);
        try {
            FileOutputStream outputStream = context.openFileOutput(path, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(score);
            objectOutputStream.close();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int loadScore(Context context){
        int result = 0;
        try {
            FileInputStream inputStream = context.openFileInput(path);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            int aux = (int) objectInputStream.readObject();

            objectInputStream.close();
            inputStream.close();

            return aux;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void addScore(int amount){
        setScore(getScore() + amount);
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
