package com.gat.vlad.gameanimattest;


/**
 * @author Vladyslav_Koveha
 */

public class CoordGexRect {

    private static final double GEX_K = 0.8660254037;

    /**
     * Координаты х и у прямоугольника описывающего данный гесагон
     */
    private int xRect;
    private int yRect;
    private int x1Rect;
    private int y1Rect;

    private int column;
    private int row;

    private int height;
    private int width;

    public CoordGexRect(int width, int column, int row) {
        this.width = width;
        this.column = column;
        this.row = row;

        height = calcHeight(width);

        calcCoord();
    }

    /**
     * Расчет высоты гексогена
     * @param width ширина
     * @return высота
     */
    private int calcHeight(int width){
        double c = width / GEX_K;
        return (int) c;
    }

    /**
     * Расчет координат
     */
    private void calcCoord() {
        if (row % 2 != 0) {
            xRect = column * width ;
        } else {
            xRect = ((column * width) + width/2);
        }
        yRect = row * ((height - (height - width/2)/2)+5);

        x1Rect = xRect + width;
        y1Rect = yRect + height;
    }

    public int getxRect() {
        return xRect;
    }

    public int getyRect() {
        return yRect;
    }

    public int getX1Rect() {
        return x1Rect;
    }

    public int getY1Rect() {
        return y1Rect;
    }
}
