package com.cs211d.joel.ballons;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Random;


public class BalloonActivity extends ActionBarActivity
{
    private FrameLayout mBt_panel;
    private CircleView  mCv;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balloon);
        final RelativeLayout frame =
                (RelativeLayout) findViewById(R.id.circleFrame);
        mBt_panel = (FrameLayout) findViewById(R.id.bt_panel);
        mBt_panel.setBackgroundColor(Color.WHITE);
        mCv = new CircleView(getApplicationContext());

        frame.addView(mCv);

    }

    /***********makeCircle()******************/

    public void makeCircle(View view)
    {
        mCv.choice = mCv.CIRCLE;
        mCv.makeCircle();
    }

    /***********clear()******************/

    public void clear(View view)
    {
        mCv.clearScreen();
        mCv.invalidate();
    }


    /*************CircleView inner-class*****/

    private class CircleView extends View
    {
        private Random rand = new Random();
        private Paint p;
        private int screenHeight;
        private int screenWidth;
        private final int CIRCLE = 0;
        private final int CLEAR = 1;
        private int choice =0;
        private ArrayList<Circle> circles = new ArrayList<>();


        /******Constructor********************/

        public CircleView(Context context)
        {
            super(context);
            p = new Paint();
            p.setColor(Color.WHITE);
            choice = CLEAR;
            p.setAntiAlias(true);
        }

        /***************onDraw()****************/

        @Override
        protected void onDraw(Canvas canvas)
        {
            canvas.drawColor(Color.WHITE);
            switch (choice)
            {
                case CIRCLE:
                {
                    for(Circle circle:circles)
                    {
                        canvas.drawCircle(circle.getX(), circle.getY(),
                                 circle.getR(),circle.getP());
                    }
                    break;
                }
                case CLEAR:
                {
                    canvas.drawColor(Color.WHITE);
                    break;
                }
            }
        }

        /**************onMeasure()**************/

        @Override
        protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
        {

            int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
            int widthSize = View.MeasureSpec.getSize(widthMeasureSpec);
            int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
            int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

            int width;
            int height;

            //Measure Width
            if ((widthMode == View.MeasureSpec.EXACTLY)
                    ||(widthMode == View.MeasureSpec.AT_MOST))
            {
                width = widthSize;
            }
            else
            {
                width = widthSize;
            }

            //Measure Height
            if ((heightMode == View.MeasureSpec.EXACTLY)
                    ||(heightMode == View.MeasureSpec.AT_MOST))
            {
                height = heightSize;
            }
            else
            {
                height = heightSize;
            }

            screenWidth = width;
            screenHeight = height-mBt_panel.getHeight();

            //Respect the bt_panels height so don't go past it
            setMeasuredDimension(width,
                    height - mBt_panel.getHeight());
        }

        /***********clearScreen()***************/

        public void clearScreen()
        {
            //set choice to Clear
            choice = CLEAR;
            //delete all circles
            circles.clear();
        }

        /***********makeCircle()***************/

        public void makeCircle()
        {
            //Create Circle Object
            Circle c = new Circle();
            c.setR(getRandomNum(1, 200));
            c.setX(getRandomNum(10, screenWidth - c.getR()));
            c.setY(getRandomNum(10, screenHeight - c.getR()));
            checkBounds(c);
            //Add newly created circle to Circle Array
            circles.add(c);

            invalidate();
        }

        /***********checkBounds()***************/

        public Rect checkBounds(Circle circle)
        {
            /**
             * Check bounds of the Circle
             * if the original bounds are off screen
             * push/pull circle to the screen
             */

            Rect bounds = circle.getBounds();

            int x = circle.getX();
            int y = circle.getY();

            if (bounds.left < 0)
            {
                circle.setX(getRandomNum(5,
                        (x + screenWidth) / 2));
            }
            else if(bounds.right > screenWidth)
            {
                circle.setX(getRandomNum(5,
                        (screenWidth - x)/2));
            }
            else if(bounds.top < 0)
            {
                circle.setY(getRandomNum(5,
                        (screenHeight + y )/ 2));
            }
            else if(bounds.bottom > screenHeight)
            {
                circle.setY(getRandomNum(5,
                        (screenHeight + y) / 2));
        }
            return bounds;
        }

        /***********getRandomNum()**************/
        public int getRandomNum(int min, int max)
        {
            //Generate random int between min-max
            return min + rand.nextInt(Math.abs((max - min)) + 1);
        }

    }




}
