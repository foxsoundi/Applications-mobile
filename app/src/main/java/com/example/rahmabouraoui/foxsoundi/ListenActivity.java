package com.example.rahmabouraoui.foxsoundi;

import android.os.Bundle;
import android.widget.Toast;
import com.android.volley.*;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a response to listen a music
 */
public class ListenActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private RequestQueue mQueue;
    private final String API_KEY = "AIzaSyBs3YEYtALR49zyTNhWDWc7VjPnVCxE8Bg";
    String video_code;
    YouTubePlayerView player;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);


        Track tr = (Track) this.getIntent().getExtras().getSerializable("music");

        mQueue = Volley.newRequestQueue(this);


        url = "https://foxsoundi2.azurewebsites.net/v1/music/youtube/"
                + tr.getName()
                + "%20"
                + tr.getArtiste()
                + "&key="
                + API_KEY ;

                url = url.replace(" ", "%20");

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            final String codeVideo = response.getString("videoId");
                            video_code = codeVideo;


                            player = (YouTubePlayerView) findViewById(R.id.player);
                            player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                    if (!b) {
                                        youTubePlayer.loadVideo(codeVideo);
                                        youTubePlayer.setPlayerStyle(YouTubePlayer.PlayerStyle.DEFAULT);
                                    }
                                }

                                @Override
                                public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                                    Toast.makeText(ListenActivity.this, youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
                                }
                            })
                            ;


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {


            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                headers.put("Content-Type", "application/json");

                return headers;
            }
        };
        mQueue.add(request);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {

    }

}
