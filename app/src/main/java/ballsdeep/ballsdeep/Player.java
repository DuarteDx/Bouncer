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
    private int y = Constants.SCREEN_HEIGHT / 5;

    private float xSpeed = 1.2f;
    private float xBoost = 0;

    private float ySpeed = 1;
    private float yVelocityWall = Constants.WALLSPEED;
    private float yVelocityAir = Constants.AIRSPEED;
    private float yGravity = 0.5f;


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

        this.x += xSpeed;
        this.y += ySpeed;

        rect.set(this.x - rect.width()/2, this.y - rect.height()/2,this.x + rect.width()/2, this.y + rect.height()/2);

    }

    public void update(Point point) {
        //left, top, right, bottom
        this.y += ySpeed;
        this.x += xSpeed;
        rect.set(this.x - rect.width()/2, this.y - rect.height()/2,this.x + rect.width()/2, this.y + rect.height()/2);

        if(this.x == 0 || this.x == Constants.SCREEN_WIDTH) {
            this.xSpeed = 0;
        }

        // While on the wall
        if(this.x == 0 || this.x == Constants.SCREEN_WIDTH) {
            this.y += this.yVelocityWall;
            this.yVelocityAir = Constants.AIRSPEED;
        }

        // While in the air
        if(this.x > 0 && this.x < Constants.SCREEN_WIDTH) {
            this.yVelocityAir += this.yGravity;
            this.y += this.yVelocityAir;
            this.yVelocityWall = Constants.WALLSPEED;
            if(this.yVelocityAir >= Constants.MINSPEED) {
                this.yVelocityAir = Constants.MINSPEED;
            }
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
            this.xSpeed += 1.5;
        }

        if(this.x <= 0) {
            this.x = 0;
            this.xSpeed = 0;
            this.yVelocityAir = Constants.AIRSPEED;
        }

        if(this.x >= Constants.SCREEN_WIDTH) {
            this.x = Constants.SCREEN_WIDTH;
            this.xSpeed = 0;
            this.yVelocityAir = Constants.AIRSPEED;
        }
    }

    public void onTouch(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // Jump RIGHT
                if(this.x == 0) {
                    this.xSpeed = 10;
                }
                // Jump LEFT
                else if(this.x == Constants.SCREEN_WIDTH) {
                    this.xSpeed = -10;
                }
        }
    }


}



