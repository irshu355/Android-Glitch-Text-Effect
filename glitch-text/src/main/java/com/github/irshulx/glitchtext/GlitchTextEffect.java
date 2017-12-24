package com.github.irshulx.glitchtext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IRSHU on 22/12/2017.
 */

public class GlitchTextEffect extends SurfaceView implements SurfaceHolder.Callback {
    private int bm_offsetY;
    private int bm_offsetX;
    private Rect textBounds;
    private Paint pathPaint;
    private Matrix matrix;
    private int step;
    private int distance;
    private float[] tan;
    private float[] pos;
    private PathMeasure pathMeasure;
    private float pathLength;
    private int noiseLevel=50;
    private Path glitchPath;
    private int height;
    private int width;
    EffectThread thread;
    List<Paint> fpsPaintList;
    int x,y;
    int xOffset,yOffset;
    private String text = "HELLO";

    public GlitchTextEffect(Context context,List<Integer> colors) {
        super(context);
        fpsPaintList = new ArrayList<>();
        this.x =  100;
        this.y = 300;
        DisplayMetrics metrics =  getContext().getResources().getDisplayMetrics();
        height =  metrics.heightPixels;
        width = metrics.widthPixels;
        setZOrderOnTop(true);

        /**
         *
         */
        pathPaint = new Paint();
        pathPaint.setColor(Color.BLUE);
        pathPaint.setStrokeWidth(1);
        pathPaint.setStyle(Paint.Style.STROKE);
        /**
         *
         */

        for(Integer color: colors){
            Paint fpsPaint = new Paint();
            fpsPaint.setTextSize(150);
            Typeface bold = FontCache.get("fonts/Poppins-Black.ttf",getContext());
            setBackgroundColor(getResources().getColor(R.color.blue));
            fpsPaint.setColor(getResources().getColor(color));
            fpsPaint.setTypeface(bold);
            fpsPaint.setStrokeWidth(10);
            fpsPaint.setAntiAlias(true);
            fpsPaintList.add(fpsPaint);
        }
        getHolder().addCallback(this);
        setFocusable(true);


        final Bitmap bitmap = getBitmap();
        Canvas canvas1 = new Canvas(bitmap);

        canvas1.drawText(text, x, y, fpsPaintList.get(2));
        textBounds = new Rect();

        fpsPaintList.get(2).getTextBounds(text,0,text.length(),textBounds);

        glitchPath =new Path();
        glitchPath.moveTo(x,y+textBounds.top-noiseLevel);
        glitchPath.lineTo(textBounds.right,y+textBounds.top-noiseLevel);
        glitchPath.lineTo(textBounds.right,textBounds.bottom);
        glitchPath.lineTo(textBounds.right ,textBounds.bottom);
        glitchPath.close();
      //  glitchPath.lineTo(bounds.right,bounds.bottom);

        pathMeasure = new PathMeasure(glitchPath, false);
        pathLength = pathMeasure.getLength();

        step = 50;
        distance = 0;
        pos = new float[2];
        tan = new float[2];

        bm_offsetX = textBounds.right/2;
        bm_offsetY = textBounds.top/2;



        matrix = new Matrix();
    }

    public GlitchTextEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlitchTextEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    public synchronized boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        thread = new EffectThread(getContext(), getHolder(), this);
        thread.setRunning(true);
        thread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {

            }
        }
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Bitmap bitmap = getBitmap();
        Canvas canvas1 = new Canvas(bitmap);
        canvas.drawPath(glitchPath, pathPaint);

        if (distance < pathLength) {
            pathMeasure.getPosTan(distance, pos, tan);
            matrix.reset();
//        canvas1.drawText(text, x, y, fpsPaintList.get(2));             //pink
            canvas1.drawText(text, pos[0],pos[1], fpsPaintList.get(1)); //blue
        canvas1.drawText(text, x, y , fpsPaintList.get(0));  //top, white color
            canvas.drawBitmap(bitmap, matrix, null);
            distance += step;
        }else{
            distance = step-1;
        }
    }

    public Bitmap getBitmap() {
        Bitmap dstBitmap = Bitmap.createBitmap(
                getContext().getResources().getDisplayMetrics(),width, // Width
                height, // Height
                Bitmap.Config.ARGB_8888 // Config
        );
        return dstBitmap;
    }

}
