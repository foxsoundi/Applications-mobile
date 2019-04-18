package com.example.rahmabouraoui.foxsoundi;

import android.app.MediaRouteButton;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MusicActivity extends AppCompatActivity {

    Button play;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        play = (Button)findViewById(R.id.button_play);
        final MediaPlayer mp = MediaPlayer.create(MusicActivity.this, R.raw.grande);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp.isPlaying()) {
                    mp.pause();
                    play.setBackgroundResource(R.drawable.ic_play_circle_outline_black_24dp);
                }
                else {
                    mp.start();
                    play.setBackgroundResource(R.drawable.ic_pause_circle_outline_black_24dp);
                }
            }
        });

    }

    public void Back(View v) {
        finish() ;
    }
}
