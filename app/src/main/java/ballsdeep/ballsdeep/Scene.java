package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * Created by Goncalo on 25/07/2017.
 */

public interface Scene {
    public void update();
    public void draw(Canvas canvas);
    public void terminate();
    public void receiveTouch(MotionEvent event);
}
