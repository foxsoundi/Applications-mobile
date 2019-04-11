package com.example.rahmabouraoui.foxsoundi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class GenresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
    }

    public void Back(View v) {
        finish() ;
    }

}
