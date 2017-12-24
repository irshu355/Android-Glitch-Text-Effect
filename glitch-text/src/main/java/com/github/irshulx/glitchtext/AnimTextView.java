package com.github.irshulx.glitchtext;

/**
 * Created by IRSHU on 24/12/2017.
 */

import android.graphics.Paint;

        import android.content.Context;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.graphics.Canvas;
        import android.graphics.Color;
        import android.graphics.Matrix;
        import android.graphics.Paint;
        import android.graphics.Path;
        import android.graphics.PathMeasure;
        import android.util.AttributeSet;
        import android.view.View;
        import android.widget.Toast;

public class AnimTextView extends View {

    Paint paint;

    Bitmap bm;
    int bm_offsetX, bm_offsetY;

    Path animPath;
    PathMeasure pathMeasure;
    float pathLength;

    float step;   //distance each step
    float distance;  //distance moved

    float[] pos;
    float[] tan;

    Matrix matrix;

    public AnimTextView(Context context) {
        super(context);
        initMyView();
    }

    public AnimTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initMyView();
    }

    public AnimTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initMyView();
    }

    public void initMyView(){
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.mine_history_icon);
        bm_offsetX = bm.getWidth()/2;
        bm_offsetY = bm.getHeight()/2;

        animPath = new Path();
        animPath.moveTo(100, 100);
        animPath.lineTo(200, 100);
        animPath.lineTo(300, 50);
        animPath.lineTo(400, 150);
        animPath.lineTo(100, 300);
        animPath.lineTo(600, 300);
        animPath.lineTo(100, 100);
        animPath.close();

        pathMeasure = new PathMeasure(animPath, false);
        pathLength = pathMeasure.getLength();

        Toast.makeText(getContext(), "pathLength: " + pathLength, Toast.LENGTH_LONG).show();

        step = 1;
        distance = 0;
        pos = new float[2];
        tan = new float[2];

        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        canvas.drawPath(animPath, paint);

        if(distance < pathLength){
            pathMeasure.getPosTan(distance, pos, tan);

            matrix.reset();
            float degrees = (float)(Math.atan2(tan[1], tan[0])*180.0/Math.PI);
            //matrix.postRotate(degrees, bm_offsetX, bm_offsetY);
            matrix.postTranslate(pos[0]-bm_offsetX, pos[1]-bm_offsetY);

            canvas.drawBitmap(bm, matrix, null);

            distance += step;
        }else{
            distance = 0;
        }

        invalidate();
    }

}