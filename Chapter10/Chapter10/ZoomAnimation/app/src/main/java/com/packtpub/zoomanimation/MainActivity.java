package com.packtpub.zoomanimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Point;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageButton;
import android.support.v7.widget.AppCompatImageView;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

public class MainActivity extends AppCompatActivity {

    private Animator mCurrentAnimator;
    private AppCompatImageView mImageViewExpanded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final AppCompatImageButton imageViewThumbnail = findViewById(R.id.imageViewThumbnail);
        imageViewThumbnail.setImageBitmap(loadSampledResource(R.drawable.image, 100, 100));
        imageViewThumbnail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomFromThumbnail(imageViewThumbnail);
            }
        });
        mImageViewExpanded = findViewById(R.id.imageViewExpanded);
        mImageViewExpanded.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mImageViewExpanded.setVisibility(View.GONE);
                mImageViewExpanded.setImageBitmap(null);
                imageViewThumbnail.setVisibility(View.VISIBLE);
            }
        });
    }

    private Bitmap loadSampledResource(int imageID, int targetHeight, int targetWidth) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(getResources(), imageID, options);
        final int originalHeight = options.outHeight;
        final int originalWidth = options.outWidth;
        int inSampleSize = 1;
        while ((originalHeight / (inSampleSize *2)) > targetHeight
                && (originalWidth / (inSampleSize *2))
                > targetWidth) {
            inSampleSize *= 2;
        }
        options.inSampleSize =inSampleSize;
        options.inJustDecodeBounds = false;
        return (BitmapFactory.decodeResource(getResources(), imageID, options));
    }

    private void zoomFromThumbnail(final AppCompatImageButton imageViewThumb) {
        if (mCurrentAnimator != null) {
            mCurrentAnimator.cancel();
        }

        final Rect startBounds = new Rect();
        final Rect finalBounds = new Rect();
        final Point globalOffset = new Point();

        imageViewThumb.getGlobalVisibleRect(startBounds);
        findViewById(R.id.frameLayout).getGlobalVisibleRect(finalBounds, globalOffset);
        mImageViewExpanded.setImageBitmap(
                loadSampledResource(R.drawable.image, finalBounds.height(), finalBounds.width()));

        startBounds.offset(-globalOffset.x, -globalOffset.y);
        finalBounds.offset(-globalOffset.x, -globalOffset.y);

        float startScale;
        if ((float) finalBounds.width() / finalBounds.height() >
                (float) startBounds.width() / startBounds.height()) {
            startScale = (float) startBounds.height() / finalBounds.height();
            float startWidth = startScale * finalBounds.width();
            float deltaWidth = (startWidth - startBounds.width()) / 2;
            startBounds.left -= deltaWidth;
            startBounds.right += deltaWidth;
        } else {
            startScale = (float) startBounds.width() / finalBounds.width();
            float startHeight = startScale * finalBounds.height();
            float deltaHeight = (startHeight - startBounds.height()) / 2;
            startBounds.top -= deltaHeight;
            startBounds.bottom += deltaHeight;
        }

        imageViewThumb.setVisibility(View.GONE);
        mImageViewExpanded.setVisibility(View.VISIBLE);
        mImageViewExpanded.setPivotX(0f);
        mImageViewExpanded.setPivotY(0f);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(ObjectAnimator.ofFloat(mImageViewExpanded, View.X,
                startBounds.left, finalBounds.left))
                .with(ObjectAnimator.ofFloat(mImageViewExpanded, View.Y,
                        startBounds.top, finalBounds.top))
                .with(ObjectAnimator.ofFloat(mImageViewExpanded, View.SCALE_X, startScale, 1f))
                .with(ObjectAnimator.ofFloat(mImageViewExpanded, View.SCALE_Y, startScale, 1f));
        animatorSet.setDuration(1000);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                mCurrentAnimator = null;
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                mCurrentAnimator = null;
            }
        });
        animatorSet.start();
        mCurrentAnimator = animatorSet;
    }
}
