package ballsdeep.ballsdeep;


import android.graphics.Canvas;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class SceneManager {
    private ArrayList<Scene> scenes = new ArrayList<>();
    public static int ACTIVESCENE;

    public SceneManager() {
        ACTIVESCENE = 0;
        scenes.add(new GameplayScene());
    }

    public void setScene(int activeScene) {
        this.ACTIVESCENE = activeScene;
    }

    public void receiveTouch(MotionEvent event) {
        scenes.get(ACTIVESCENE).receiveTouch(event);
    }

    public void update() {
        scenes.get(ACTIVESCENE).update();
    }

    public void draw(Canvas canvas) {
        scenes.get(ACTIVESCENE).draw(canvas);
    }

}
