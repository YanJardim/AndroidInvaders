package com.example.developer.androidinvaders;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.provider.MediaStore;

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

    public void playSFX(Context context, String sfxName)
    {
        //NÃO FUNFA AINDA//
        SoundPool newSFX = null;
        int soundEffect = 0;
        try {

            newSFX = new SoundPool(20, AudioManager.STREAM_MUSIC, 0);
            AssetFileDescriptor descriptorSFX = context.getAssets().openFd(sfxName);

            soundEffect = newSFX.load(descriptorSFX, 1);
            System.out.println(newSFX);
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        newSFX.play(soundEffect,1,1,0,0,1);
    }

}
