package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by developer on 17/04/17.
 */

public class Explosion {
    public static final int ALIVE = 0;
    public static final int DEAD = 1;



    Particle[] particles;

    public int currentState = DEAD;

    int tamanho = 0;

    float currentLife = 0;

    public Explosion(int amountOfParticles, float x, float y){
        loadParticles(amountOfParticles, x, y);
    }

    public boolean isAlive(){
        return currentState == ALIVE;
    }

    public boolean isDead()
    {

            return currentLife >= particles[0].tempoDeVida;
    }


    public void loadParticles(int amount,
                              float x, float y){
        currentState = ALIVE;
        particles = new Particle[amount];
        for(int i = 0; i< particles.length; i++){
            Particle p = new Particle(x,y);
            particles[i] = p;
        }
        tamanho = amount;
    }

    public void update(float deltaTime) {
        if(isAlive())
        {
            currentLife += deltaTime / 1000;

            boolean newState = true;
            for(int i = 0; i< particles.length; i++){
                if(particles[i].isAlive()){
                    particles[i].update(deltaTime);
                    newState = false;
                }
            }
            if(newState){
                currentState = DEAD;
            }

        }
    }

    public void draw(Canvas canvas, Paint paint) {
        if(isAlive()){
            for(int i = 0; i< particles.length; i++) {
                if (particles[i].isAlive()) {
                    particles[i].draw(canvas, paint);
                }
            }
        }
    }
}
