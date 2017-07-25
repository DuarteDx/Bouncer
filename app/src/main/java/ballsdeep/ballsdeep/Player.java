package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class Player implements GameObject{

    private Rect rect;
    private int color;

    public Player(Rect rect, int color)  {
        this.rect = rect;
        this.color = color;
    }

    public Rect getRect() {return rect;}

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rect, paint);
    }

    @Override
    public void update() {

    }

    public void update(Point point) {
        //left, top, right, bottom
        rect.set(point.x - rect.width()/2, point.y - rect.height()/2,point.x + rect.width()/2, point.y + rect.height()/2);
    }
}



