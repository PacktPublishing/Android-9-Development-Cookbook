package com.packtpub.customview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class CustomView extends View {

    final Paint mPaint = new Paint();

    public CustomView(Context context) {
        super(context);
        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(30);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackgroundColor(Color.CYAN);
        canvas.drawText("Custom Text", 100, 100, mPaint);
        invalidate();
    }
}
