package com.example.developer.androidinvaders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        int full = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(full);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_menu);
    }

    public void startGame(View view)
    {
        Intent gameplay = new Intent().setClass(view.getContext(), MainActivity.class);

        view.getContext().startActivity(gameplay);
    }
}
