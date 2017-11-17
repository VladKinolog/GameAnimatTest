package com.gat.vlad.gameanimattest;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.Random;

/**
 * au
 */

public class GroundTexture {
    private static final int ROWS = 2;
    private static final int COLUMS = 3;
    private int x = 0;
    private int y = 0;
    private GameView gameView;
    private Bitmap bitmap;
    private int currentFrame = 0;
    private int width;
    private int height;
    private float [] canvTranslate = {0.0f,0.0f};
    private double canvScal = 1.0;

    private GameBoard gb = new GameBoard(16,16);

    private int s = 0;

    public GroundTexture(GameView gameView, Bitmap bitmap) {
        this.gameView = gameView;
        this.bitmap = bitmap;
        this.width = bitmap.getWidth() / COLUMS;
        this.height = bitmap.getHeight() / ROWS;
    }

    private void update(int s){
        currentFrame = s;
//        if (currentFrame < COLUMS) {
//            currentFrame++;
//        } else {
//            currentFrame = 0;
//        }
    }

    public void onDrow(Canvas canvas){

        canvas.translate(canvTranslate[0], canvTranslate[1]);
        canvas.scale((float) canvScal,(float) canvScal);

        int scrX = 0;
        int scrY = 0;
        Rect scr = new Rect(scrX, scrY, scrX + width, scrY + height);

            for (GexagonUnit gexUnit : gb.getGexUnits()) {

                Rect dst = new Rect(gexUnit.getCoord().getxRect(), gexUnit.getCoord().getyRect(), gexUnit.getCoord().getX1Rect(), gexUnit.getCoord().getY1Rect());
                canvas.drawBitmap(bitmap, scr, dst, null);
            }



    }

    public float[] getCanvTranslate() {
        return canvTranslate;
    }

    public void setCanvTranslate(float[] canvTranslate) {
        this.canvTranslate = canvTranslate;
    }

    public double getCanvScal() {
        return canvScal;
    }

    public void setCanvScal(double canvScal) {
        this.canvScal = canvScal;
    }
}








///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/*          for (int i = 0; i<10;i++){
            int scrY;
            s = new Random().nextInt(3);
            update(s);
            int scrX = currentFrame * width;
            if ( new Random().nextBoolean()) {
                scrY = 0;//height;
            } else {scrY = height;}

            Rect scr = new Rect(scrX,scrY,scrX + width,scrY + height);
            Rect dst = new Rect((i*(x+width)+x)/5 - 100, y/5 - 50 ,(i*(x+width)+(x+width))/5 - 100,(y+height)/5 - 50);
            canvas.drawBitmap(bitmap,scr,dst,null);
        }
*/