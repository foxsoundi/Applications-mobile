package com.app.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Represents a response from tracks's categories
 */
public class YourMusicActivity extends AppCompatActivity {

    private long id;
    private String title;
    private String artist;

    public YourMusicActivity(long songID, String songTitle, String songArtist) {
        id = songID;
        title = songTitle;
        artist = songArtist;
    }

    public long getID() {
        return id;
    }
    public String getTittle() {
        return title;
    }
    public String getArtist() {
        return artist;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_music);
    }

    public void yourMusic(View view) {
        Intent intent = new Intent(this, YourMusicActivity.class);
        startActivity(intent);
    }

    public void Back(View v) {
        finish() ;
    }
}
