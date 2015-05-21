package com.cs211d.joel.ballons;

/*
  Author: Joel Rainey
  Date: 5/21
  Class: CS211D Spring 2015
  Android Project: Balloons
  Filename: Circle.java
  Assignment Objective: Create an application that randomly draws a circle on screen when the
  user press "Balloon" Button
*/

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.Random;


public class Circle
{

    private int x;
    private int y;
    private int r;
    private Paint p;
    private Random rand = new Random();


    /******Constructor***********/
    public Circle()
    {
        //A new paint color is generated randomly for Circle Obj
        p = new Paint();
        p.setColor(Color.rgb(getRandomNum(25, 255),
                getRandomNum(1, 255), getRandomNum(25, 255)));
    }

    /***********getRandomNum()*********************************/
    public int getRandomNum(int min, int max)
    {
        //Generate random int between min-max
        return min + rand.nextInt((Math.abs(max - min) + 1));
    }

    /******GettersAndSetters*****/
    public Paint getP()
    {
        return p;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    public void setY(int y)
    {
        this.y = y;
    }

    public void setR(int r)
    {
        this.r = r;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getR()
    {
        return r;
    }



}
