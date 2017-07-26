package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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

    private Rect r = new Rect();

    private boolean gameOver = false;


    public GameplayScene() {
        player = new Player(new Rect(100,100,200,200), Color.WHITE);

        obstacleManager = new ObstacleManager();
    }

    @Override
    public void update() {
        if (gameOver) return;
        player.update();

        obstacleManager.update(player);

        if(obstacleManager.playerCollide(player)) {
            gameOver = true;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.BLACK);

        player.draw(canvas);
        obstacleManager.draw(canvas);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.WHITE);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    // andreas1724 (white color):
    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
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
