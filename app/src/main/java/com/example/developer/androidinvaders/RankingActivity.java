package com.example.developer.androidinvaders;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RankingActivity extends AppCompatActivity {

    private List<Row> rows;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View decor = getWindow().getDecorView();
        int full = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decor.setSystemUiVisibility(full);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_ranking);

        SoundManager.getInstance().loadSounds(getBaseContext(), "Sounds/Select.mp3");

        rows = new ArrayList<Row>();
        rows.add(new Row((TextView)findViewById(R.id.txtIndex0),
                (TextView)findViewById(R.id.txtName0),
                (TextView)findViewById(R.id.txtScore0)));
        rows.add(new Row((TextView)findViewById(R.id.txtIndex1),
                (TextView)findViewById(R.id.txtName1),
                (TextView)findViewById(R.id.txtScore1)));
        rows.add(new Row((TextView)findViewById(R.id.txtIndex2),
                (TextView)findViewById(R.id.txtName2),
                (TextView)findViewById(R.id.txtScore2)));
        rows.add(new Row((TextView)findViewById(R.id.txtIndex3),
                (TextView)findViewById(R.id.txtName3),
                (TextView)findViewById(R.id.txtScore3)));
        rows.add(new Row((TextView)findViewById(R.id.txtIndex4),
                (TextView)findViewById(R.id.txtName4),
                (TextView)findViewById(R.id.txtScore4)));




        List<Score> scores = ScoreManager.getInstance().loadScores(decor.getContext());
        //rows.get(0).set(0, scores.get(0).getName(), scores.get(0).getScore());
        for(int i = 0; i < scores.size(); i++){
            rows.get(i).set(i, scores.get(i).getName(), scores.get(i).getScore());
        }

        for(int i = scores.size(); i < rows.size(); i++){
            rows.get(i).remove();
        }

    }

    public class Row{
        public TextView index;
        public TextView name;
        public TextView score;

        public Row(TextView index, TextView name, TextView score){
            this.index = index;
            this.name = name;
            this.score = score;
        }

        public void set(int index, String name, int score){
            this.index.setText(index + "");
            this.name.setText(name);
            this.score.setText(score + "");
        }

        public void remove(){
            index.setVisibility(View.GONE);
            name.setVisibility(View.GONE);
            score.setVisibility(View.GONE);
        }
    }

    public void startGame(View view)
    {
        Intent gameplay = new Intent().setClass(view.getContext(), MainActivity.class);
        SoundManager.getInstance().playMP3("Select", 0.7f);
        view.getContext().startActivity(gameplay);
    }

    public void resetSave(View view)
    {
        SoundManager.getInstance().playMP3("Select", 0.7f);
        ScoreManager.getInstance().resetSave(view.getContext());
    }

}


