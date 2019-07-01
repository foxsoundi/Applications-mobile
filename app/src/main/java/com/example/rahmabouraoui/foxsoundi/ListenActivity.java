package com.example.rahmabouraoui.foxsoundi;

import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ListenActivity extends YouTubeBaseActivity {

    private final String API_KEY = "AIzaSyBs3YEYtALR49zyTNhWDWc7VjPnVCxE8Bg";
    private final String VIDEO_CODE = "XfP31eWXli4";
    YouTubePlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        Track tr = (Track) this.getIntent().getExtras().getSerializable("music") ;


        //wv = (WebView) findViewById( R.id.webView ) ;

        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl("http://perdu.com");

        player = (YouTubePlayerView) findViewById(R.id.player);
        player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                if(!b){
                    youTubePlayer.loadVideo(VIDEO_CODE);
                    youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                }
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(ListenActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}
