package com.github.irshulx.glitchtext;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IRSHU on 22/12/2017.
 */

public class GlitchTextEffect extends FrameLayout{
    private String text = "HELLO";
    List<TextView> textViews = new ArrayList<>();
    private AnimationSet animationSet;
    private long duration=2000;

    public GlitchTextEffect(Context context,List<Integer> colors) {
        super(context);
        for(Integer color: colors){
            TextView textView = new TextView(getContext());
            textView.setGravity(Gravity.CENTER);
            textView.setTypeface(FontCache.get("fonts/Poppins-Black.ttf",getContext()));
            textView.setTextColor(getResources().getColor(color));
            textView.setText(text);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 80);
            addView(textView);
            textViews.add(textView);
            if(textViews.size()!=3) {
                replace(textView, 10, textViews.size());
            }
        }
    }

    public void replace(final TextView textView,int noise, final int size) {
        // create set of animations
        animationSet = new AnimationSet(false);
        // animations should be applied on the finish line

        // create translation animation
        TranslateAnimation trans = new TranslateAnimation(0, 0,
                TranslateAnimation.ABSOLUTE, -1*noise , 0, 0,
                TranslateAnimation.ABSOLUTE, noise);
        trans.setDuration(duration);

        TranslateAnimation trans2 = new TranslateAnimation(0, 0,
                TranslateAnimation.ABSOLUTE, noise, 0, 0,
                TranslateAnimation.ABSOLUTE, -1*noise*2);
        trans2.setStartOffset(duration);
        trans2.setDuration(duration);



        TranslateAnimation trans3 = new TranslateAnimation(0, 0,
                TranslateAnimation.ABSOLUTE, noise, 0, 0,
                TranslateAnimation.ABSOLUTE, noise*2);
        trans3.setStartOffset(2*duration);
        trans3.setDuration(duration);


        TranslateAnimation trans4 = new TranslateAnimation(0, 0,
                TranslateAnimation.ABSOLUTE, -1*noise, 0, 0,
                TranslateAnimation.ABSOLUTE, -1*noise*1);
        trans4.setStartOffset(3*duration);
        trans4.setDuration(duration);


        // add new animations to the set
        animationSet.addAnimation(trans);
        animationSet.addAnimation(trans2);
        animationSet.addAnimation(trans3);
        animationSet.addAnimation(trans4);

        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                replace(textView,10,1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        // start our animation
        animationSet.setStartOffset((size-1)*duration);
        textView.startAnimation(animationSet);
    }


    public GlitchTextEffect(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GlitchTextEffect(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
