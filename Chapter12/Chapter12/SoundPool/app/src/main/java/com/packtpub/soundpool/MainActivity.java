package com.packtpub.soundpool;

import android.annotation.TargetApi;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    HashMap<Integer, Integer> mHashMap= null;
    SoundPool mSoundPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button button1 = findViewById(R.id.button1);
        button1.setEnabled(false);
        final Button button2 = findViewById(R.id.button2);
        button2.setEnabled(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            createSoundPoolNew();
        } else {
            createSoundPoolOld();
        }
        mSoundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                button1.setEnabled(true);
                button2.setEnabled(true);
            }
        });
        mHashMap = new HashMap<>();
        mHashMap.put(1, mSoundPool.load(this, R.raw.sound_1, 1));
        mHashMap.put(2, mSoundPool.load(this, R.raw.sound_2, 1));
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void createSoundPoolNew() {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();
        mSoundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
    }

    @SuppressWarnings("deprecation")
    private void createSoundPoolOld(){
        mSoundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
    }

    public void playSound1(View view){
        mSoundPool.play(mHashMap.get(1), 0.1f, 0.1f, 1, 0, 1.0f);
    }
    public void playSound2(View view){
        mSoundPool.play(mHashMap.get(2), 0.9f, 0.9f, 1, 1, 1.0f);
    }

    @Override
    protected void onStop() {
        mSoundPool.release();
        super.onStop();
    }
}
