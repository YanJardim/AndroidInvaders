package com.example.developer.androidinvaders;

import java.io.Serializable;

/**
 * Created by yan on 27/05/2017.
 */

public class Score implements Serializable{
    private int score;
    private String name;


    public Score(){
        score = 0;
        name = "AAA";
    }
    public Score(int score){
        this.score = score;
    }
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addScore(int amount){
        setScore(getScore() + amount);
    }
}
