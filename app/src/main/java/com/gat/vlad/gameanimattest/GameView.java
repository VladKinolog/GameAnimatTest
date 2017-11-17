package com.gat.vlad.gameanimattest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import static android.content.ContentValues.TAG;

/**
 * Created by VLAD on 14.11.2017.
 */

public class GameView extends SurfaceView {

    private static final String TAG = "GameView";

    private GameLoopTread gameLoopTread;
    private GroundTexture grTexture;
    private Bitmap bmp;
    private SurfaceHolder holder;
    private int speed = 1;

    double x = 0;
    double y = 0;
    double x1 = 0;
    double y1 = 0;
    double canvScale = 1.0;
    double deltaX = 0;

    public GameView(Context context) {
        super(context);
        gameLoopTread = new GameLoopTread(this);
        holder = getHolder();

        holder.addCallback(new SurfaceHolder.Callback() {


            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                gameLoopTread.setGameLoopRunning(true);
                gameLoopTread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                boolean retry = true;
                gameLoopTread.setGameLoopRunning(false);
                    while (retry){
                        try {
                         gameLoopTread.join();
                            retry = false;
                        } catch (InterruptedException e){

                        }
                    }

            }
        });

        bmp = BitmapFactory.decodeResource(getResources(),R.drawable.gexo_ground);
        grTexture = new GroundTexture(this,bmp);
    }

    protected void onDr(Canvas canvas) {
        canvas.drawColor(Color.GRAY);
        grTexture.onDrow(canvas);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        Log.i(TAG, "коснулись");

        int actionMask = event.getActionMasked();
        int pointerIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();

        float [] canTr = grTexture.getCanvTranslate();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN:
                x = event.getX(0);
                y = event.getY(0);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                deltaX = Math.abs((event.getX(0)) - (x1 = event.getX(1)));
                break;

            case MotionEvent.ACTION_UP:
                break;

            case MotionEvent.ACTION_POINTER_UP:
                if (pointerIndex == 1) {
                    x = event.getX(0);
                    y = event.getY(0);
                } else {
                    x = event.getX(1);
                    y = event.getY(1);
                }
                break;

            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    canTr[0] = (float) ((float) canTr[0] + (event.getX() - x));
                    canTr[1] = (float) ((float) canTr[1] + (event.getY() - y));

                    x = event.getX(0);
                    y = event.getY(0);

                    synchronized (getHolder()) {
                        grTexture.setCanvTranslate(canTr);
                    }
                } else {
                    double dX = Math.abs((event.getX(0)) - (x1 = event.getX(1)));
                        //y1 = event.getY(1);
                    canvScale = grTexture.getCanvScal() + (dX - deltaX)/1080;
                    deltaX = dX;

                    synchronized (getHolder()) {
                        grTexture.setCanvScal(canvScale);
                    }
                }
                break;
                //Log.i(TAG, "onDragEvent: getX() = " + x + ", getY() = " + y);
        }
        return true;

    }
}
