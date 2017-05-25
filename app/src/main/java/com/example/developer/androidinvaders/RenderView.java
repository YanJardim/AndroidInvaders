package com.example.developer.androidinvaders;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by developer on 03/04/17.
 */

public class RenderView extends View {
    private Paint paint = new Paint();
    private float startTime = 0;
    private Context context;
    private float timer = 0;
    private final int timeProjetilSpawn = 1000;
    private float maxTimer = timeProjetilSpawn;
    private Player player;

    public List<Projetil> projetilList = new ArrayList<>();
    private TextGameObject textScore;
    public int score = 0;

    private EnemiesController enemiesController;
    private List<Explosion> explosions = new ArrayList<>();
    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();

        this.context = context;

        inputs();

        textScore = new TextGameObject();

        textScore.color = Color.YELLOW;
        textScore.size = 50;
        textScore.x = 50;
        textScore.y = 50;

        //GameResources.getInstance().addObject(textScore);

        enemiesController = new EnemiesController(context, this);

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        if (GameResources.getInstance().gameObjectList.size() != 0) return;
        createPlayer(getWidth()/2,getHeight()-50, 150);

        enemiesController.initEnemies();

    }

    public void Update(float deltaTime)
    {

        if(timer >= maxTimer)
        {
            maxTimer += timeProjetilSpawn;
            createProjetil();
        }

        textScore.text = "Score: " + score;


        Collisions();
    }

    public void inputs()
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
        GameResources.getInstance().addObject(spaceShip);
    }

    public void createProjetil()
    {
        Projetil projetil = new Projetil("Sprites/Projetil.png", context.getAssets(), this);

        float ratio = ScreenUtils.getScaleRelativeByScreen(getWidth(), getHeight(), 0.025f);
        projetil.bitmap = projetil.scale(projetil.bitmap,ratio,true);
        projetil.x = player.x;
        projetil.y = player.y;
        projetil.name = "Projetil";

        projetilList.add(projetil);
    }
    public void createExplosion(float x, float y){
        explosions.add(new Explosion(10, x, y));
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
        for(int i =0; i< projetilList.size();i++)
        {
            for(int j =0;j< enemiesController.enemies.size(); j++)
            {
                if (projetilList.get(i).Collision(enemiesController.enemies.get(j))) {
                    createExplosion(enemiesController.enemies.get(j).getX(), enemiesController.enemies.get(j).getY());
                    System.out.println(enemiesController.enemies.get(i).getX() + "," + enemiesController.enemies.get(i).getY());
                    enemiesController.enemies.remove(j);
                    projetilList.remove(i);

                    score++;
                    return;
                }

            }
        }
    }


    @Override
    public void draw(Canvas canvas)
    {
        super.draw(canvas);
        float deltaTime = (System.nanoTime() - startTime) / 1000000;
        timer += deltaTime;
        canvas.drawRGB(0,0,0);

        Update(deltaTime);
        updateAndDrawProjetil(canvas,paint,deltaTime);

        textScore.update(deltaTime);
        textScore.draw(canvas,paint);

        GameResources.getInstance().updateAndDraw(deltaTime, canvas, paint);
        //enemiesController.movementEnemies(deltaTime);
        enemiesController.drawAndUpdate(canvas, paint, deltaTime);
        updateAndDrawExplosions(canvas, paint, deltaTime);

        //showColliders(canvas, paint);

        startTime = System.nanoTime();
        invalidate();
    }

    public void showColliders(Canvas canvas, Paint paint){
        for(int i =0;i< enemiesController.enemies.size();i++)
        {
            enemiesController.enemies.get(i).drawRect(canvas,paint);
        }
    }

}
