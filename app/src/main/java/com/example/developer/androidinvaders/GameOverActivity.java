package com.example.developer.androidinvaders;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity {
    private TextView txtScore;
    private TextView txtScoreValue;
    private EditText inptName;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        int full = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(full);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_game_over);


        SoundManager.getInstance().loadSounds(getBaseContext(), "Sounds/Select.mp3");
        setComponents(decor);
        setFonts();

    }

    public void setComponents(View view){
        txtScore =(TextView)findViewById(R.id.txtScore0);
        txtScoreValue =(TextView)findViewById(R.id.txtScoreValue);
        inptName = (EditText) findViewById(R.id.inptName);
        btnSave = (Button) findViewById(R.id.btnSave);

        txtScoreValue.setText(ScoreManager.getInstance().getScore().getScore() + "");

    }

    public void setFonts(){

        Typeface enigmaFont= Typeface.createFromAsset(getAssets(), "fonts/Enigma.ttf");

        txtScore.setTypeface(enigmaFont);
        txtScoreValue.setTypeface(enigmaFont);
    }

    public void startGame(View view)
    {
        Intent gameplay = new Intent().setClass(view.getContext(), MainActivity.class);
        SoundManager.getInstance().playMP3("Select", 0.7f);
        view.getContext().startActivity(gameplay);
    }

    public void saveRankings(View view){
        ScoreManager.getInstance().getScore().setName(inptName.getText().toString());
        ScoreManager.getInstance().saveScore(view.getContext());

        SoundManager.getInstance().playMP3("Select", 0.7f);
        btnSave.setVisibility(View.GONE);
        inptName.setVisibility(View.GONE);
    }

    public void showRankings(View view){
        Intent rankings = new Intent().setClass(view.getContext(), RankingActivity.class);
        SoundManager.getInstance().playMP3("Select", 0.7f);
        view.getContext().startActivity(rankings);
    }
}
