package com.example.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class ListenActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener{

    private static final int RECOVERY_REQUEST=1;
    private YouTubePlayerView youTubeView;
    private final String API_KEY = "AIzaSyBs3YEYtALR49zyTNhWDWc7VjPnVCxE8Bg";

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listen);

        youTubeView=(YouTubePlayerView) findViewById(R.id.player);
        youTubeView.initialize(API_KEY,this);
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer player, boolean wasRestored){
        if(!wasRestored){
        player.cueVideo("fhWaJi1Hsfo"); // Plays https://www.youtube.com/watch?v=fhWaJi1Hsfo
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult errorReason){
        if(errorReason.isUserRecoverableError()){
            errorReason.getErrorDialog(this,RECOVERY_REQUEST).show();
        }else{
            //String error=String.format(getString(R.string.player_error),errorReason.toString());
            Toast.makeText(this,"error",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode==RECOVERY_REQUEST){
        // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(API_KEY,this);
        }
    }

    protected YouTubePlayer.Provider getYouTubePlayerProvider(){
        return youTubeView;
    }
}
//        } {
//
//    private RequestQueue mQueue;
//    private final String API_KEY = "AIzaSyBs3YEYtALR49zyTNhWDWc7VjPnVCxE8Bg";
//    private final String VIDEO_CODE = "XfP31eWXli4";
//    YouTubePlayerView player;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_listen);
//
//    }
//}


        //Track tr = (Track) this.getIntent().getExtras().getSerializable("music") ;
        //System.out.println("listen : " + tr);


        //wv = (WebView) findViewById( R.id.webView ) ;

        //wv.getSettings().setJavaScriptEnabled(true);
        //wv.loadUrl("http://perdu.com");


       /* mQueue = Volley.newRequestQueue(this);

        System.out.println("Video");


        //String url = "http://foxsoundi2.azurewebsites.net/v1/music/playlist/" + idPL + "/tracks" ;




        String url = "https://www.googleapis.com/youtube/v3/search?part="
                + tr.getName()
                + "%20"
                + tr.getArtiste()
                + "&key="
                *//*+ API_KEY*//* ;

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
            *//**
             * Passing some request headers*
             *//*
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
        System.out.println("3");*/
    //}


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


//}
