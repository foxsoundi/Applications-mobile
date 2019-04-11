package com.example.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }

    public void search(View view) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void yourMusic(View view) {
        Intent intent = new Intent(this, YourMusicActivity.class);
        startActivity(intent);
    }

    public void Back(View v) {
        finish() ;
    }
}
