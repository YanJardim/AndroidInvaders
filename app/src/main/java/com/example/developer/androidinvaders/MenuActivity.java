package com.example.developer.androidinvaders;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        int full = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(full);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);


        SoundManager.getInstance().loadSounds(getBaseContext(), "Sounds/Select.mp3");
        TextView txtTitle =(TextView)findViewById(R.id.txtTitle);
        Typeface face= Typeface.createFromAsset(getAssets(), "fonts/Enigma.ttf");
        txtTitle.setTypeface(face);

    }

    public void startGame(View view)
    {
        Intent gameplay = new Intent().setClass(view.getContext(), MainActivity.class);
        SoundManager.getInstance().playMP3("Select", 0.7f);

        view.getContext().startActivity(gameplay);
    }
}
