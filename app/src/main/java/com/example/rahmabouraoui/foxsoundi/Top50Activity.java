package com.example.rahmabouraoui.foxsoundi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Top50Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top50);
    }

    public void Back(View v) {
        finish() ;
    }
}
