package com.packtpub.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mMediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonPlay(View view){
        if (mMediaPlayer==null) {
            mMediaPlayer = MediaPlayer.create(this, R.raw.sound_1);
            mMediaPlayer.setLooping(true);
            mMediaPlayer.start();
        } else  {
            mMediaPlayer.start();
        }
    }

    public void buttonPause(View view){
        if (mMediaPlayer!=null && mMediaPlayer.isPlaying()) {
            mMediaPlayer.pause();
        }
    }

    public void buttonStop(View view){
        if (mMediaPlayer!=null) {
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mMediaPlayer!=null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }
}
