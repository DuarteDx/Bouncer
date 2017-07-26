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
    private boolean scored = false;

    public Obstacle(int rectHeight, int color, int width, int startY, boolean left)  {
        this.color = color;
        if (left) {
            rect = new Rect(0, startY, width, startY + rectHeight);
        }
        else {
            rect = new Rect(Constants.SCREEN_WIDTH - width, startY, Constants.SCREEN_WIDTH, startY + rectHeight);
        }
    }

    public Rect getRectangle() {
        return rect;
    }

    public boolean getScored() {
        return scored;
    }

    public void setScored() {
        scored = true;
    }

    public void incrementY(float y) {
        rect.top += y;
        rect.bottom += y;
    }

    public boolean playerCollides(Player player) {
        return Rect.intersects(rect, player.getRect());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }
}
