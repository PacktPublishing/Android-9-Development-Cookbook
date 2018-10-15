package com.packtpub.hardwaremediacontrols;

import android.media.session.MediaSession;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.PlaybackStateCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    MediaSessionCompat.Callback mMediaSessionCallback = new MediaSessionCompat.Callback() {
        @Override
        public void onPlay() {
            super.onPlay();
            Toast.makeText(MainActivity.this, "onPlay()", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onPause() {
            super.onPause();
            Toast.makeText(MainActivity.this, "onPause()", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onSkipToNext() {
            super.onSkipToNext();
            Toast.makeText(MainActivity.this, "onSkipToNext()", Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onSkipToPrevious() {
            super.onSkipToPrevious();
            Toast.makeText(MainActivity.this, "onSkipToPrevious()", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MediaSessionCompat mediaSession =
                new MediaSessionCompat(this, getApplication().getPackageName());
        mediaSession.setCallback(mMediaSessionCallback);
        mediaSession.setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS);
        mediaSession.setActive(true);
        PlaybackStateCompat state = new PlaybackStateCompat.Builder()
                .setActions(PlaybackStateCompat.ACTION_PLAY |
                        PlaybackStateCompat.ACTION_PLAY_PAUSE |
                        PlaybackStateCompat.ACTION_PAUSE |
                        PlaybackStateCompat.ACTION_SKIP_TO_NEXT |
                        PlaybackStateCompat.ACTION_SKIP_TO_PREVIOUS).build();
        mediaSession.setPlaybackState(state);
    }
}
