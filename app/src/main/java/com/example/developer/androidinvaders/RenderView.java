package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 03/04/17.
 */

public class RenderView extends View {
    private Paint paint = new Paint();
    private float startTime = 0;
    private Context context;
    private float timer = 0, posDeathTimer = 0;
    private final int timeProjetilSpawn = 350;

    private Player player;

    public List<Projetil> projetilList = new ArrayList<>();
    private TextGameObject txtScore, txtGameOver, txtScoreGameOver,txtHighScore;
    public int score = 0;
    public int highPublic = 0;

    private EnemiesController enemiesController;
    private List<Explosion> explosions = new ArrayList<>();

    long last_time = System.nanoTime();

    public RenderView(Context context) {
        super(context);
        this.context = context;

        setInputs();
        initTexts();

        enemiesController = new EnemiesController(context, this);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (GameResources.getInstance().gameObjectList.size() != 0) return;
        createPlayer(getWidth()/2,getHeight()-50, 150);

        enemiesController.initEnemies(new Vector2(getWidth() *0.1f, getHeight() * 0.1f), 1, 1);

    }

    public void Update(float deltaTime)
    {

        if(player.isDead()) {
            posDeathTimer += deltaTime /1000;
            if(posDeathTimer >= 2)
                return;
        }

        if(!player.isDead()) {

            if (timer >= timeProjetilSpawn) {
                createProjetil();
                timer = 0;
            }
        }

        txtScore.setText("Score: " + score);

        for(int i =0; i<explosions.size();i++)
        {
            if(explosions.get(i).isDead())
            {
                explosions.remove(i);
            }
        }

       /* if(enemiesController.enemies.size() == 0){

            enemiesController.enemies.initEnemies(new Vector2(getWidth() *0.1f,
                    getHeight() * 0.1f),
                    (int)enemiesController.amountEnemies.getX() + 1,
                    (int)enemiesController.amountEnemies.getY() +1);

        }*/


        Collisions();
    }

    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        long time = System.nanoTime();
        float deltaTime = ((time - last_time) / 1000000);
        //float deltaTime = (System.nanoTime() - startTime) / 1000000;
        //deltaTime = 1;
        timer += deltaTime;
        canvas.drawRGB(0,0,0);

        if(posDeathTimer >= 2) {
            int lastScore = ScoreManager.getInstance().loadScore(context);

            if(score > lastScore) {
                ScoreManager.getInstance().saveScore(context, score);
            }


            txtHighScore.setText("HighScore: " + ScoreManager.getInstance().loadScore(context));
            txtHighScore.setX(txtHighScore.getCenter(canvas, paint).getX()-10);
            txtHighScore.setY(txtHighScore.getCenter(canvas, paint).getY()+50);

            txtHighScore.draw(canvas, paint);
            txtGameOver.setText("GameOver");
            txtGameOver.setX(txtGameOver.getCenter(canvas, paint).getX()-110);
            txtGameOver.setY(txtGameOver.getCenter(canvas, paint).getY());

            txtScoreGameOver.setText("Current Score: " + score);
            txtScoreGameOver. setX(txtScoreGameOver.getCenter(canvas, paint).getX() + 50);
            txtScoreGameOver.setY(txtScoreGameOver.getCenter(canvas,paint).getY()+ 100);


            txtScoreGameOver.draw(canvas,paint);
            txtGameOver.draw(canvas,paint);
            return;
        }


        Update(deltaTime);


        txtScore.update(deltaTime);
        txtScore.draw(canvas,paint);

        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);

        enemiesController.moveEnemies(deltaTime);
        enemiesController.drawAndUpdate(canvas, paint, deltaTime);


        updateAndDrawExplosions(canvas, paint, deltaTime);

        if(!player.isDead()) {
            updateAndDrawProjetil(canvas,paint,deltaTime);
            player.update(deltaTime);
            player.draw(canvas, paint);
        }

        //showColliders(canvas, paint);

        last_time= time;
        invalidate();
    }

    public void initTexts(){
        txtScore = new TextGameObject("Score: ", Color.WHITE, 50, new Vector2(50, 50));

        txtGameOver = new TextGameObject("Game Over", Color.RED, 100, new Vector2((getWidth() / 2),
                getHeight() / 2));

        txtHighScore = new TextGameObject("HighScore", Color.WHITE, 50, new Vector2(getWidth() / 2,(getHeight() /2)));

        txtScoreGameOver =  new TextGameObject("Current Score: ", Color.WHITE, 30, new Vector2(getWidth()/2,getHeight()/2));
    }

    public void setInputs()
    {
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                if(event.getAction() == MotionEvent.ACTION_DOWN)
                {
                    if(event.getX() > getWidth()/2)
                    {
                        player.move = player.speed;
                    }
                    else
                    {
                        player.move = -player.speed;

                    }

                }
                else if(event.getAction() == MotionEvent.ACTION_UP)
                {
                    player.move = 0;
                }
                return true;
            }

        });
    }

    public void createPlayer(int x, int y, int scale)
    {
        Player spaceShip = new Player("Sprites/player.png",context.getAssets(),this);

        spaceShip.bitmap = spaceShip.scale(spaceShip.bitmap,
                ScreenUtils.getScaleRelativeByScreen(getWidth(), getHeight(), 0.15f), true);
        spaceShip.x = x;
        spaceShip.y = y;
        spaceShip.name = "SpaceShip";
        player = spaceShip;
    }

    public void createProjetil()
    {
        Projetil projectile = new Projetil("Sprites/Projetil.png", context.getAssets(), this, "PlayerProjetil");

        float ratio = ScreenUtils.getScaleRelativeByScreen(getWidth(), getHeight(), 0.025f);
        projectile.bitmap = projectile.scale(projectile.bitmap,ratio,true);
        projectile.x = player.x;
        projectile.y = player.y;
        projectile.name = "Projetil";

        projetilList.add(projectile);
    }
    public void createExplosion(float x, float y){
        explosions.add(new Explosion(50, x, y));
    }

    public void updateAndDrawProjetil(Canvas canvas, Paint paint, float deltaTime)
    {
        for(int i =0; i< projetilList.size();i++)
        {
            projetilList.get(i).update(deltaTime);
            projetilList.get(i).draw(canvas,paint);

            if(projetilList.get(i).y < 0)
            {
                projetilList.remove(i);
            }
        }
    }
    public void updateAndDrawExplosions(Canvas canvas, Paint paint, float deltaTime)
    {
        for(int i =0; i< explosions.size();i++)
        {
            explosions.get(i).update(deltaTime);
            explosions.get(i).draw(canvas,paint);

            if(explosions.get(i).isDead())
            {
                explosions.remove(i);
            }
        }
    }

    public void Collisions()
    {
        projectilAndEnemiesCollision();
        playerAndEnemiesCollision();
    }

    public void projectilAndEnemiesCollision(){
        for(int i =0; i< projetilList.size();i++)
        {
            for(int j =0;j< enemiesController.enemies.size(); j++)
            {
                if (projetilList.get(i).Collision(enemiesController.enemies.get(j))) {
                    createExplosion(enemiesController.enemies.get(j).getBoudingBox().centerX(),
                            enemiesController.enemies.get(j).getBoudingBox().centerY());

                    enemiesController.enemies.remove(j);
                    enemiesController.changeEnemiesSpeed();
                    projetilList.remove(i);

                    score++;
                    return;
                }

            }
        }
    }

    public void playerAndEnemiesCollision(){

        for(int j =0;j< enemiesController.enemies.size(); j++)
        {
            if (player.Collision(enemiesController.enemies.get(j))) {
                createExplosion(player.getBoudingBox().centerX(), player.getBoudingBox().centerY());
                enemiesController.enemies.remove(j);
                player.setDead(true);
                return;
            }

        }

    }



    public void showColliders(Canvas canvas, Paint paint){
        for(int i =0;i< enemiesController.enemies.size();i++)
        {
            enemiesController.enemies.get(i).drawRect(canvas,paint);
        }
    }

}
