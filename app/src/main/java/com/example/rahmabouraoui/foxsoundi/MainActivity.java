package com.example.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    private TextView mTextMessage;
    GridView gridView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_genre:
                    mTextMessage.setText(R.string.title_genre);
                    return true;
                case R.id.navigation_top50:
                    mTextMessage.setText(R.string.title_top50);
                    return true;
                case R.id.navigation_new:
                    mTextMessage.setText(R.string.title_new);
                    return true;
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_search);
                    return true;
                case R.id.navigation_account:
                    mTextMessage.setText(R.string.title_account);
                    return true;

            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        navigation.setBackgroundColor(Color.TRANSPARENT);
        navigation.setItemTextColor(ColorStateList.valueOf(Color.GRAY));
        navigation.setItemIconTintList(ColorStateList.valueOf(Color.GRAY));

        /*gridView = (GridView) findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(this, genres, images);
        gridView.setAdapter(gridAdapter);

        gridView.setNumColumns(2);*/

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void goGenres(MenuItem item) {
        Intent intent = new Intent(this, GenresImagesActivity.class);
        startActivity(intent);
    }

    public void goPlaylist(MenuItem item) {
        Intent intent = new Intent(this, MusicActivity.class);
        startActivity(intent);
    }

    public void goTop50(MenuItem item) {
        Intent intent = new Intent(this, Top50Activity.class);
        startActivity(intent);
    }

    public void goNew(MenuItem item) {
        Intent intent = new Intent(this, NewActivity.class);
        startActivity(intent);
    }

    public void goSearch(MenuItem item) {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    public void goAccount(MenuItem item) {
        Intent intent = new Intent(this, AccountActivity.class);
        startActivity(intent);
    }



    String[] genres = {
            "Pop",
            "Rock",
            "Electronic",
            "Rap",
            "Indie",
            "Jazz"

    };

    int[] images = {
            R.mipmap.pop,
            R.mipmap.rock,
            R.mipmap.electro,
            R.mipmap.rap,
            R.mipmap.indie,
            R.mipmap.jazz,
    };

}
