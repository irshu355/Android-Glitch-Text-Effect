package com.github.irshulx.glitcheffectdemo;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.github.irshulx.glitchtext.AnimTextView;
import com.github.irshulx.glitchtext.GlitchTextEffect;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        List<Integer> colors = new ArrayList<>();
        colors.add(R.color.colorAccent);
        colors.add(R.color.colorPrimaryDark);
        colors.add(R.color.colorPrimary);
//        AnimTextView textView = new AnimTextView(this);
//        setContentView(textView);
        setContentView(R.layout.activity_main);
        FrameLayout layout =(FrameLayout)findViewById(R.id.frame);
        GlitchTextEffect effect = new GlitchTextEffect(this,colors);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = Gravity.CENTER;
        layout.addView(effect);
    }
}
