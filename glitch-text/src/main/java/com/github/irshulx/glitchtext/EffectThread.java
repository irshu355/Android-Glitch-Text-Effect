package com.github.irshulx.glitchtext;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.SurfaceHolder;
/**
 * Created by IRSHU on 22/12/2017.
 */
public class EffectThread extends Thread {
    private Context context;
    private SurfaceHolder surfaceHolder;
    private GlitchTextEffect textEffect;
    private boolean run;
    private long timeNow;
    private long timeDelta;
    private long timePrevFrame;

    public EffectThread(Context context, SurfaceHolder surfaceHolder, GlitchTextEffect textEffect) {
        this.context = context;
        this.surfaceHolder = surfaceHolder;
        this.textEffect = textEffect;
    }

    public void setRunning(boolean run) {
        this.run = run;
    }

    public SurfaceHolder getSurfaceHolder() {
        return surfaceHolder;
    }

    @Override
    public void run() {
        Canvas c;
        while (run){
            c = null;
            timeNow = System.currentTimeMillis();
            timePrevFrame = System.currentTimeMillis();
            long diff = timeNow - timePrevFrame ;

            try {
                c = surfaceHolder.lockCanvas(null);
                    c.drawColor(context.getResources().getColor(R.color.blue), PorterDuff.Mode.CLEAR);
                   textEffect.onDraw(c);
            } finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }
        }