package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class GameplayScene implements Scene {

    private SceneManager manager;

    private Player player;
    private ObstacleManager obstacleManager;

    private boolean gameOver = false;


    public GameplayScene() {
        player = new Player(new Rect(100,100,200,200), Color.WHITE);

        obstacleManager = new ObstacleManager();
    }

    @Override
    public void update() {
        player.update();

        obstacleManager.update();

        if(obstacleManager.playerCollide(player)) {
            gameOver = true;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        player.draw(canvas);
        obstacleManager.draw(canvas);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVESCENE = 0;
    }

    @Override
    public void receiveTouch(MotionEvent event) {
        player.onTouch(event);
    }
}
