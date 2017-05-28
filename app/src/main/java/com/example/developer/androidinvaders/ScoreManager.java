package com.example.developer.androidinvaders;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yan on 27/05/2017.
 */

public class ScoreManager {
    private static ScoreManager ourInstance = new ScoreManager();

    private Score score;

    public static ScoreManager getInstance() {
        if(ourInstance == null){
            ourInstance = new ScoreManager();
        }

        return ourInstance;
    }
    private final int maxScores = 5;
    private final String fileName = "Score.ani";

    public void saveScore(Context context){
        saveScore(context, this.score);
    }

    public void saveScore(Context context, Score score){
        this.setScore(score);
        System.out.println(score.getScore() + " - " + score.getName());
        List<Score> scores = loadScores(context);
        if(scores == null) scores = new ArrayList<Score>();
        scores.add(score);
        Collections.sort(scores, new Comparator<Score>() {
            @Override
            public int compare(Score o1, Score o2) {
                if(o1.getScore() == o2.getScore())
                    return 0;
                return o1.getScore() < o2.getScore() ? 1 : -1;
            }
        });

        if(scores.size() > maxScores){
            for (int i = maxScores - 1; i < scores.size(); i++){
                scores.remove(i);
            }
        }

        try {

            FileOutputStream outputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(scores);
            objectOutputStream.close();
            outputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Score> loadScores(Context context){

        try {
            FileInputStream inputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            List<Score> scores = (List<Score>) objectInputStream.readObject();


            System.out.println("Elements: " + scores.size());
            for (Score s : scores) {
                System.out.println(s.getScore());
            }

            objectInputStream.close();
            inputStream.close();

            return scores;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


    public int getHighScore(Context context){
        List<Score> scores = loadScores(context);

        if(scores == null)
            return -1;

        int highScore = 0;
        for (Score s: scores) {
            //System.out.println(s.getScore() + " - " + highScore);
            if(s.getScore() > highScore)
                highScore = s.getScore();
        }
        return  highScore;
    }

    public boolean resetSave(Context context){
        File dir = context.getFilesDir();
        File file = new File(dir, fileName);
        return file.delete();
    }

    public Score getScore() {
        return score;
    }

    public void setScore(Score score) {
        this.score = score;
    }
}
