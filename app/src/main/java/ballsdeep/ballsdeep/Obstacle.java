package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.constraint.solver.widgets.Rectangle;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class Obstacle implements GameObject {

    private Rect rect;
    private Rect rect2;
    private int color;
    private int startX;
    private int playerGap;

    public Obstacle(int rectHeight, int color, int startX, int startY, int playerGap)  {
        this.color = color;
        rect = new Rect(0, startY, startX, startY + rectHeight);
        rect2 = new Rect(startX + playerGap, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
    }

    public Rect getRectangle() {
        return rect;
    }

    public void incrementY(float y) {
        rect.top += y;
        rect.bottom += y;
        rect2.top += y;
        rect2.bottom += y;
    }

    public boolean playerCollides(Player player) {
        return Rect.intersects(rect, player.getRect()) || Rect.intersects(rect2, player.getRect());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
        canvas.drawRect(rect2, paint);
    }

    @Override
    public void update() {

    }
}
