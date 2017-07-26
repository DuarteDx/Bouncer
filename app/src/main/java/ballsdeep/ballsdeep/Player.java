package ballsdeep.ballsdeep;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

/**
 * Created by Goncalo on 25/07/2017.
 */

public class Player implements GameObject{

    private Rect rect;
    private int color;

    private int x = 0;
    private int y = Constants.SCREEN_HEIGHT/2;

    private float xVelocity = 0f;

    private float yVelocityWall = Constants.WALLSPEED;
    private float yVelocityAir;
    private float yGravity = Constants.GRAVITY;


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
        this.x += this.xVelocity;
        System.out.println(Constants.SCREEN_WIDTH);

        // While on the wall
        if(this.x <= 0 || this.x >= Constants.SCREEN_WIDTH) {
            this.yVelocityAir = Constants.AIRSPEED;
            this.y += this.yVelocityWall;
            //System.out.println("On wall");
        }

        // While in the air
        if(this.x > 0 && this.x < Constants.SCREEN_WIDTH) {
            System.out.println("On air");
            this.yVelocityAir += this.yGravity;
            this.y += this.yVelocityAir;
            this.yVelocityWall = Constants.WALLSPEED;
            /*if(this.yVelocityAir >= Constants.MINSPEED) {
                this.yVelocityAir = Constants.MINSPEED;
            }*/
        }

        // In the floor
        if(this.y >= Constants.SCREEN_HEIGHT) {
            this.y = Constants.SCREEN_HEIGHT;
            this.yVelocityAir = Constants.AIRSPEED;
            this.y += this.yVelocityAir;
        }

        // In the ceiling
        if(this.y <= 0) {
            this.y = 0;
            this.yVelocityAir = -Constants.AIRSPEED/6;
            this.xVelocity *= 0.3;
        }

        if(this.x <= 0) {
            this.x = 0;
            this.xVelocity = 0;
            this.yVelocityAir = Constants.AIRSPEED;
        }

        if(this.x >= Constants.SCREEN_WIDTH) {
            this.x = Constants.SCREEN_WIDTH;
            this.xVelocity = 0;
            this.yVelocityAir = Constants.AIRSPEED;
        }
        //left, top, right, bottom
        rect.set(this.x - rect.width()/2, this.y - rect.height()/2,this.x + rect.width()/2, this.y + rect.height()/2);
    }

    public void onTouch(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Jump RIGHT
                if(this.x == 0) {
                    this.xVelocity = Constants.XSPEED;
                }
                // Jump LEFT
                else if(this.x == Constants.SCREEN_WIDTH) {
                    this.xVelocity = -Constants.XSPEED;
                }
        }
    }


}



