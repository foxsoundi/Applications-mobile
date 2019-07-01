package com.example.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GenreMusicActivity extends AppCompatActivity {

    GridLayout grille;
    List<Playlist> lesPLs = new ArrayList<Playlist>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_genre_music);
        grille = (GridLayout) findViewById(R.id.mainGridPL) ;

        mQueue = Volley.newRequestQueue(this);

        System.out.println("PlayLists");

        String idGenre = this.getIntent().getExtras().getString( "id" ) ;

        String url = "http://foxsoundi2.azurewebsites.net/v1/music/genre/" + idGenre + "/playlists" ;
        System.out.println("1 => " + url );
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Requete");
                        try {
                            JSONObject jsonPLs = response.getJSONObject("playlists");
                            JSONArray jsonItems = jsonPLs.getJSONArray("items");

                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonPL = jsonItems.getJSONObject(i);
                                Playlist pl = new Playlist();
                                pl.setName(jsonPL.getString("name"));
                                pl.setId(jsonPL.getString("id"));
                                pl.setHref(jsonPL.getString("href"));
                                JSONArray jsonImgs = jsonPL.getJSONArray("images");
                                JSONObject jsonImg = jsonImgs.getJSONObject(0);
                                pl.setUrlImage(jsonImg.getString("url"));
                                lesPLs.add(pl);
                                Log.i("PL ; " , pl.toString());
                                System.out.println(pl.toString());

                            }

                            for(Playlist pl : lesPLs) {
                                System.out.println("PL $$$ : " + pl) ;
                                new GenreMusicActivity.DownLoadImageTask(GenreMusicActivity.this.grille, pl).execute();
                                /*
                                LinearLayout linearLayout = new LinearLayout(GenresImagesActivity.this);
                                TextView textGenre = new TextView(GenresImagesActivity.this);
                                textGenre.setText(genre.getName());
                                LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                linearLayout.addView(textGenre, paramText);
                                grille.addView(linearLayout);
                                */
                            }


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
        });
        System.out.println("2");
        mQueue.add(request);
        System.out.println("3");
    }

    public void Back(View v) {
        finish() ;
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;
        GridLayout grille;
        Playlist pl;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        public DownLoadImageTask(GridLayout grille, Playlist pl) {
            this.grille = grille;
            this.pl = pl;
        }

        /*
                            doInBackground(Params... params)
                                Override this method to perform a computation on a background thread.
                         */
        protected Bitmap doInBackground(String...urls){
            //String urlOfImage = urls[0];
            String urlOfImage = pl.getUrlImage() ;
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }

        /*
            onPostExecute(Result result)
                Runs on the UI thread after doInBackground(Params...).
         */
        protected void onPostExecute(Bitmap result){
            System.out.println( "VERTICAL" ) ;
            LinearLayout linearLayout = new LinearLayout(GenreMusicActivity.this);
            //linearLayout.setGravity(LinearLayout.VERTICAL);
            ImageView imgPL = new ImageView(GenreMusicActivity.this);
            imgPL.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println( "PL : " + pl.getName() ) ;

                    Bundle bdl = new Bundle() ;
                    bdl.putString( "id" , pl.getId() );
                    Intent intent = new Intent(GenreMusicActivity.this, TrackActivity.class);
                    intent.putExtras( bdl ) ;
                    startActivity(intent);

                }
            });
            imgPL.setImageBitmap(result);
            linearLayout.addView(imgPL);
            TextView textGenre = new TextView(GenreMusicActivity.this);
            textGenre.setText(pl.getName());
            LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(textGenre, paramText);
            grille.addView(linearLayout);

        }
    }
}
