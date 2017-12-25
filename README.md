# Android-Glitch-Text-Effect

An Android library to add a glitch effect into a string. This project was inspired by the codepen, https://codepen.io/ihatetomatoes/pen/8a16e11e6d6206f5eb1ed7a37fbbe1dc.

Demo screen below was captured at a limited 25fps 

![captured at 25fps](https://github.com/irshuLx/Android-Glitch-Text-Effect/raw/master/screens/ezgif-3.gif)


Download
------------
gradle:

    compile 'com.github.irshulx:glitch-text-effect:1.0.0'

or maven:

    <dependency>
      <groupId>com.github.irshulx</groupId>
      <artifactId>glitch-text-effect</artifactId>
      <version>1.0.0</version>
      <type>pom</type>
    </dependency>


Usage
--------------

    <com.github.irshulx.glitchtext.GlitchTextEffect
        android:layout_width="match_parent"
        app:text="PURGE"
        app:noise="7"
        app:textColors="@array/glitch_colors"
        app:textSize="27"
        android:layout_height="match_parent" />

	        

or programmatically:
				
	List<Integer> colors = new ArrayList<>();
        colors.add(R.color.colorPrimaryDark);
        colors.add(R.color.colorPink);
        colors.add(R.color.colorLightBlue);
        colors.add(R.color.colorFront);
        
        GlitchTextEffect effect = new GlitchTextEffect(this,colors,"PURGE");
        effect.setTextSize(60);
        effect.setNoise(6); //the noise quotient of the effect
        effect.start();     // required to call for programmatic initiation
        layout.addView(effect);
