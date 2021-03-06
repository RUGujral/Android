package com.example.android.assignment4touch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

public class TouchActivity extends AppCompatActivity {
    private float mPreviousX=0;
    private float mPreviousY=0;
    private float x=0;
    private float y=0;
    ArrayList<Float> Xvalues;
    ArrayList<Float> Yvalues;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new DrawingView(this));
    }

    class DrawingView extends SurfaceView {

        private final SurfaceHolder surfaceHolder;
        private final Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

        public DrawingView(Context context) {
            super(context);
            surfaceHolder = getHolder();
            paint.setColor(Color.RED);
        }

        @Override
        public boolean onTouchEvent(MotionEvent e) {
            // MotionEvent reports input details from the touch screen
            // and other input controls. In this case, you are only
            // interested in events where the touch position changed.

            float x = e.getX();
            float y = e.getY();
            if (e.getAction() == MotionEvent.ACTION_MOVE) {
                float dx = x - mPreviousX;
                float dy = y - mPreviousY;

                if (surfaceHolder.getSurface().isValid()) {
                    Canvas canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.WHITE);
                    canvas.drawCircle(dx, dy, 10, paint); // (dx,dy,x,y,paint);
                    paint.setTextSize(25);
                    canvas.drawText("Current X: " + dx + "  Current Y: " + dy + " Pointer count " + e.getPointerId(0), 40, 50, paint);
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            return true;
        }


    }
}
