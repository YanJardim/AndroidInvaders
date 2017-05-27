package com.example.developer.androidinvaders;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by yan on 25/05/2017.
 */

public class SoundManager {

    public static SoundManager instance;

    public static SoundManager getInstance(){
        if(instance == null){
            instance = new SoundManager();
        }
        return instance;
    }

    public void PlaySound(Context context, String audioName){
        //SoundPool pool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);
    }


}
