[ ![Download](https://api.bintray.com/packages/irshu/maven/glitch-text-effect/images/download.svg) ](https://bintray.com/irshu/maven/glitch-text-effect/_latestVersion)

# Android-Glitch-Text-Effect

An Android library to add a glitch effect into a string. This project was inspired by the codepen, https://codepen.io/ihatetomatoes/pen/8a16e11e6d6206f5eb1ed7a37fbbe1dc.

Demo screen below was captured at a limited 25fps 

![captured at 25fps](https://github.com/irshuLx/Android-Glitch-Text-Effect/raw/master/screens/ezgif-3.gif)&nbsp; &nbsp; ![captured at 25fps](https://raw.githubusercontent.com/irshuLx/Android-Glitch-Text-Effect/master/screens/ezgif.gif)


Download
------------
gradle:

    compile 'com.github.irshulx:glitch-text-effect:1.0.1'

or maven:

    <dependency>
      <groupId>com.github.irshulx</groupId>
      <artifactId>glitch-text-effect</artifactId>
      <version>1.0.1</version>
      <type>pom</type>
    </dependency>


A gist of this library is available at https://gist.github.com/irshuLx/138c1dc0af5262e3ca0c5f5db014d7ef

Usage
--------------

    <com.github.irshulx.glitchtext.GlitchTextEffect
        android:layout_width="match_parent"
        app:text="PURGE"
        app:noise="7"
        app:textColors="@array/glitch_colors"
        app:textSize="27"
       	app:speed="70"
        android:layout_height="match_parent" />

	        

or programmatically:
				
	List<Integer> colors = new ArrayList<>();
        colors.add(R.color.colorPrimaryDark);
        colors.add(R.color.colorPink);
        colors.add(R.color.colorLightBlue);
        colors.add(R.color.colorFront);
        
        GlitchTextEffect effect = new GlitchTextEffect(this,colors,"PURGE");
        effect.setTextSize(60);
	effect.setSpeed(70); //duration of a single cycle animation
        effect.setNoise(6); //the noise quotient of the effect
        effect.start();     // required to call for programmatic initiation
        layout.addView(effect);

The logic is to stack textviews on a FrameLayout and animate their positions in opposite directions. The no. of textviews added is proportional to the number of colors in the array.

![enter image description here](https://raw.githubusercontent.com/irshuLx/Android-Glitch-Text-Effect/master/screens/img_css-glitch-effect-animation.png)

API
--------------

    void setTextSize(int size);    // set the textsize in sp
    
    void setNoise(int level);     // set the noise level, recommended between 5-10
    
    void setFontFile(String fileName); // set the font to be used, eg: "fonts/Poppins-Black.ttf"
    
    void setSpeed(int speed); // speed in ms, recommends 50-200


##License

    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
    http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
