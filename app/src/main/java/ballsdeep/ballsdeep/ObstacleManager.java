package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class ObstacleManager {

    private ArrayList<Obstacle> obstacles;
    private int playerGap = 200;
    private int obstacleGap = 350;
    private int obstacleHeight = 75;

    private int minWidth = 100;
    private int maxWidth = 250;

    private int score = 0;

    private boolean left = false;
    private int color = Color.RED;

    private long startTime;

    public ObstacleManager() {
        startTime = System.currentTimeMillis();

        obstacles = new ArrayList<>();

        populateObstacles();
    }

    public boolean playerCollide(Player player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollides(player)) {
                return true;
            }
        }
        return false;
    }

    private void populateObstacles() {
        int currY = -5*Constants.SCREEN_HEIGHT/4;

        while (currY < 0) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            obstacles.add(new Obstacle(obstacleHeight, color, (int)(Math.random() * (maxWidth - minWidth) + minWidth), currY, left));
            left = !left;
            currY += obstacleHeight + obstacleGap;
        }
    }

    public void update(Player player) {
        int elapsedTime = (int) (System.currentTimeMillis() - startTime);
        startTime = System.currentTimeMillis();
        float speed = Constants.SCREEN_HEIGHT/10000.0f;

        for (Obstacle ob : obstacles) {
            ob.incrementY(speed * elapsedTime);
            //if (ob.getRectangle().top < player.getRect().top && player.)
        }

        if (obstacles.get(obstacles.size() - 1).getRectangle().top >= Constants.SCREEN_HEIGHT) {
            int xStart = (int) (Math.random() * (Constants.SCREEN_WIDTH - playerGap));
            int yStart = obstacles.get(0).getRectangle().top - obstacleHeight - obstacleGap;
            obstacles.add(0, new Obstacle(obstacleHeight, color, (int)(Math.random() * (maxWidth - minWidth) + minWidth), yStart, left));
            left = !left;
            obstacles.remove(obstacles.size() - 1);
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles) {
            ob.draw(canvas);
        }
        Paint paint = new Paint();
        paint.setTextSize(100);
        paint.setColor(Color.MAGENTA);
        canvas.drawText("" + score, 50, 50 + paint.descent() - paint.ascent(), paint);
    }

}







