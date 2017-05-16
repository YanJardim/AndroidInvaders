package com.example.developer.androidinvaders;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Random;

/**
 * Created by developer on 17/04/17.
 */

public class Particles {
    public static final int ESTADO_VIDA = 0;
    public static final int ESTADO_MORTA = 1;

    public static final int TEMPO_DE_VIDA = 200;
    public static final int TAMANHO_MAXIMO = 15;
    public static final int VELOCIDADE_MAXIMA = 20;

    int estado;

    double xv,yv;

    int idade;
    int tempoDeVida;
    int cor;
    int alpha,red,green,blue;

    /*public boolean isVida(){
        return estado == ESTADO_VIDA;
    }
    public boolean isMorta(){
        return estado == ESTADO_MORTA;
    }
    public Particles(float x, float y){
        this.setX(x);
        this.setY(y);

        estado = ESTADO_VIDA;
        Random r = new Random();
        setWidth(r.nextInt(TAMANHO_MAXIMO) + 1);
        setHeight(getWidth());

        tempoDeVida = TEMPO_DE_VIDA;
        idade = 0;
        alpha = 255;
        red = r.nextInt(255);
        green = r.nextInt(255);
        blue = r.nextInt(255);

        cor = Color.argb(alpha,red,
                green,blue);


        xv = r.nextDouble()* VELOCIDADE_MAXIMA *2-
                VELOCIDADE_MAXIMA;
        yv = r.nextDouble()* VELOCIDADE_MAXIMA *2-
                VELOCIDADE_MAXIMA;

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        if(isVida()){
            setX(getX() + xv * deltaTime * 0.1);
            setY(getY() + yv * deltaTime * 0.1);

            alpha -= 2;
            idade++;
            if(idade >= tempoDeVida ||
                    alpha <= 0){
                estado = ESTADO_MORTA;
            }else{
                cor = Color.argb(alpha,red,
                        green,blue);
            }

        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        paint.setColor(cor);
        paint.setAlpha(alpha);
        canvas.drawRect(getX(), getY(),
                getX() + getWidth(), getY() + getHeight(),paint);
        paint.reset();
    }*/
}
