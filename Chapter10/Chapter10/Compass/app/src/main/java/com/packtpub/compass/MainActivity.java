package com.packtpub.compass;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

public class MainActivity extends AppCompatActivity {

    private SensorManager mSensorManager;
    private Sensor mMagnetometer;
    private Sensor mAccelerometer;
    private AppCompatImageView mImageViewCompass;
    private float[] mGravityValues=new float[3];
    private float[] mAccelerationValues=new float[3];
    private float[] mRotationMatrix=new float[9];
    private float mLastDirectionInDegrees = 0f;

    private SensorEventListener mSensorListener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            calculateCompassDirection(event);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int
                accuracy) {
            //Nothing to do
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mImageViewCompass = findViewById(R.id.imageViewCompass);
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    private void calculateCompassDirection(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerationValues = event.values.clone();
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mGravityValues = event.values.clone();
                break;
        }
        boolean success = SensorManager.getRotationMatrix(mRotationMatrix, null,
                mAccelerationValues, mGravityValues);
        if (success) {
            float[] orientationValues = new float[3];
            SensorManager.getOrientation(mRotationMatrix, orientationValues);
            float azimuth = (float) Math.toDegrees(-orientationValues[0]);
            RotateAnimation rotateAnimation = new RotateAnimation(mLastDirectionInDegrees, azimuth,
                    Animation.RELATIVE_TO_SELF, 0.5f,
                    Animation.RELATIVE_TO_SELF, 0.5f);
            rotateAnimation.setDuration(50);
            rotateAnimation.setFillAfter(true);
            mImageViewCompass.startAnimation(rotateAnimation);
            mLastDirectionInDegrees = azimuth;
        }
    }

        @Override
    protected void onResume() {
        super.onResume();
        mSensorManager.registerListener(mSensorListener, mMagnetometer,
                SensorManager.SENSOR_DELAY_FASTEST);
        mSensorManager.registerListener(mSensorListener, mAccelerometer,
                SensorManager.SENSOR_DELAY_FASTEST);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorManager.unregisterListener(mSensorListener);
    }
}
