package com.example.developer.androidinvaders;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public void saveScore(Context context){
        byte[] bytes = ByteBuffer.allocate(4).putInt(score).array();
        try {
            FileOutputStream outputStream = context.openFileOutput(path, Context.MODE_PRIVATE);
            outputStream.write(bytes);
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
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            String total = "";
            while((line = reader.readLine()) != null){
                total += (line);
            }
            result = Integer.parseInt(total);
            reader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
