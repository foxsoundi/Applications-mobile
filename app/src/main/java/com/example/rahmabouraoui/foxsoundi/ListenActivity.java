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

public class ListenActivity extends YouTubeBaseActivity {

    private RequestQueue mQueue;
    private final String API_KEY = "AIzaSyBs3YEYtALR49zyTNhWDWc7VjPnVCxE8Bg";
    private final String VIDEO_CODE = "XfP31eWXli4";
    YouTubePlayerView player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        Track tr = (Track) this.getIntent().getExtras().getSerializable("music") ;
        System.out.println("listen : " + tr);


        //wv = (WebView) findViewById( R.id.webView ) ;

        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl("http://perdu.com");


        mQueue = Volley.newRequestQueue(this);

        System.out.println("Video");


        //String url = "http://foxsoundi2.azurewebsites.net/v1/music/playlist/" + idPL + "/tracks" ;




        String url = "https://www.googleapis.com/youtube/v3/search?part="
                + tr.getName()
                + "%20"
                + tr.getArtiste()
                + "&key="
                + API_KEY ;

        url = url.replace(" ","%20") ;


        System.out.println("VIDEO 1 => " + url );
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Requete");
                        try {
                            final String codeVideo = response.getString("kind");
                            System.out.println( "VIDEO KIND " + codeVideo  ) ;


                            player = (YouTubePlayerView) findViewById(R.id.player);
                            player.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
                                @Override
                                public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                                    if(!b){
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
                            System.out.println("e1");
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("e2");
                error.printStackTrace();
            }
        }) {
            /**
             * Passing some request headers*
             */
            @Override
            public Map getHeaders() throws AuthFailureError {
                HashMap headers = new HashMap();
                //headers.put("Authorization", "Bearer "); // TOKEN
                //headers.put("Authorization", "Bearer "); // TOKEN
                headers.put("Content-Type", "application/json");
                //headers.put("apiKey", API_KEY);

                return headers;
            }
        };
        System.out.println("2");
        mQueue.add(request);
        System.out.println("3");
    }


/*
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
*/


}
