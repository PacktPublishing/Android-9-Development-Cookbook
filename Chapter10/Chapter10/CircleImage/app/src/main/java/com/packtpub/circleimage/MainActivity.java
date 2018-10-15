package com.packtpub.circleimage;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ImageDecoder;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.PostProcessor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends Activity {

    PostProcessor mCirclePostProcessor = new PostProcessor() {
        @Override
        public int onPostProcess(Canvas canvas) {
            Path path = new Path();
            path.setFillType(Path.FillType.INVERSE_EVEN_ODD);
            int width = canvas.getWidth();
            int height = canvas.getHeight();
            path.addCircle(width/2,height/2,600, Path.Direction.CW);
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            paint.setColor(Color.TRANSPARENT);
            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC));
            canvas.drawPath(path, paint);
            return PixelFormat.TRANSLUCENT;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadImage();
    }

    private void loadImage() {
        ImageDecoder.Source source = ImageDecoder.createSource(getResources(),
                R.drawable.stars);

        ImageDecoder.OnHeaderDecodedListener listener =
                new ImageDecoder.OnHeaderDecodedListener() {
            public void onHeaderDecoded(ImageDecoder decoder, ImageDecoder.ImageInfo info,
                                        ImageDecoder.Source source) {
                decoder.setPostProcessor(mCirclePostProcessor);
            }
        };
        try {
            Drawable drawable = ImageDecoder.decodeDrawable(source, listener);
            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
