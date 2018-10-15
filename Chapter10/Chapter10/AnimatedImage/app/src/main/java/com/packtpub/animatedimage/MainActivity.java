package com.packtpub.animatedimage;

import android.app.Activity;
import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadGif();
    }

    private void loadGif() {
        try {
            ImageDecoder.Source source = ImageDecoder.createSource(getResources(),
                    R.drawable.giphy);
            Drawable decodedAnimation = ImageDecoder.decodeDrawable(source);

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageDrawable(decodedAnimation);

            if (decodedAnimation instanceof AnimatedImageDrawable) {
                ((AnimatedImageDrawable) decodedAnimation).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
