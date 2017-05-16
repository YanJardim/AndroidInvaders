package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Paint;

/**
 * Created by developer on 17/04/17.
 */

public class Explosion_Particles  {
    /*public static final int ESTADO_VIDA = 0;
    public static final int ESTADO_MORTA = 1;

    Particles[] particles;

    public int estado = ESTADO_MORTA;

    int tamanho = 0;

    public boolean isVida(){
        return estado == ESTADO_VIDA;
    }
    public boolean isMorta(){
        return estado == ESTADO_MORTA;
    }
    public void loadParticulas(int numeroDeParticulas,
                          int x,int y){
        estado = ESTADO_VIDA;
        particles = new Particles[numeroDeParticulas];
        for(int i = 0; i< particles.length; i++){
            Particles p = new Particles(x,y);
            particles[i] = p;
        }
        tamanho = numeroDeParticulas;
    }

    @Override
    public void update(float deltaTime) {
        if(isVida()){
            boolean morta = true;
            for(int i = 0; i< particles.length; i++){
                if(particles[i].isVida()){
                    particles[i].update(deltaTime);
                    morta = false;
                }
            }
            if(morta){
                estado = ESTADO_MORTA;
            }
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        if(isVida()){
            for(int i = 0; i< particles.length; i++) {
                if (particles[i].isVida()) {
                    particles[i].draw(canvas, paint);
                }
            }
        }
    }*/
}
