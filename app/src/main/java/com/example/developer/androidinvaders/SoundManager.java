package com.example.developer.androidinvaders;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.AudioManager;
import android.media.SoundPool;
import android.provider.MediaStore;

import java.util.HashMap;
import java.util.List;

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

    public HashMap<String, SoundPool> hashSound = new HashMap<String,SoundPool>();

    public void loadSounds(Context context, String... audioName)
    {
        try
        {
            for(int i =0; i< audioName.length; i++)
            {
                SoundPool newSoundPool = new SoundPool(20,AudioManager.STREAM_MUSIC,0);
                AssetFileDescriptor descriptormp3 = context.getAssets().openFd(audioName[i]);
                newSoundPool.load(descriptormp3, 1);
                hashSound.put(audioName[i],newSoundPool);
            }

        }catch (Exception e)
        {
            e.printStackTrace();
        }


    }

    public SoundPool getSound(String AudioName)
    {
        if(hashSound.containsKey(AudioName))
        {
            return hashSound.get(AudioName);
        }

        return null;
    }

}
