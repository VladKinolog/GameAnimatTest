package com.gat.vlad.gameanimattest;

import android.graphics.Canvas;
import android.util.Log;
import android.view.View;

/**
 * @author Vladyslav_Koveha
 */

public class GameLoopTread extends Thread {
    private static final int FPS = 20;
    private GameView gameView;
    private boolean gameLoopRunning = false;

    private static final String TAG = "GameLoopTread";

    public GameLoopTread(GameView gameView) {
        this.gameView = gameView;
    }

    public GameView getGameView() {
        return gameView;
    }

    public void setGameLoopRunning(boolean gameLoopRunning) {
        this.gameLoopRunning = gameLoopRunning;
    }

    @Override
    public void run() {
        long tickPS = 1000/FPS;
        long startTime;
        long sleepTime;
        while (gameLoopRunning) {
            Canvas canvas = null;
            startTime = System.currentTimeMillis();

            try {
            canvas = gameView.getHolder().lockCanvas();
                synchronized (gameView.getHolder()) {
                    gameView.onDr(canvas);
                }
            }finally {
                if (canvas != null) {
                    gameView.getHolder().unlockCanvasAndPost(canvas);
                }
            }
            sleepTime = tickPS - (System.currentTimeMillis() - startTime);
            Log.i(TAG, "run: sleepTime =" + sleepTime);
                    try {
                        if (sleepTime > 0) {
                            sleep (sleepTime);
                        } else {
                            sleep(10);
                        }
                    } catch (InterruptedException e){

                    }

        }

    }
}
