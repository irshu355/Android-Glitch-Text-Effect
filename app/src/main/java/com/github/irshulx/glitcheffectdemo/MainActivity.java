package com.github.irshulx.glitcheffectdemo;

import android.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;
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
        colors.add(R.color.colorPrimary);
        colors.add(R.color.colorAccent);
        colors.add(R.color.colorPrimaryDark);
//        AnimTextView textView = new AnimTextView(this);
//        setContentView(textView);
        setContentView(R.layout.activity_main);
        LinearLayout layout =findViewById(R.id.x);
        GlitchTextEffect effect = new GlitchTextEffect(this,colors);
        layout.addView(effect);
    }
}
