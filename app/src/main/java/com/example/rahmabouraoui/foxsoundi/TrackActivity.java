package com.example.rahmabouraoui.foxsoundi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Represents a response from tracks's categories
 */
public class TrackActivity extends AppCompatActivity {



    GridLayout grille;
    List<Track> lesTRs = new ArrayList<Track>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_track);
        grille = (GridLayout) findViewById(R.id.mainGridTR) ;

        mQueue = Volley.newRequestQueue(this);

        String idPL = this.getIntent().getExtras().getString( "id" ) ;

        String url = "http://foxsoundi2.azurewebsites.net/v1/music/playlist/" + idPL + "/tracks" ;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");

                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);

                                Track tr = new Track();
                                JSONObject jsonTR = jsonItem.getJSONObject("track");

                                tr.setId(jsonTR.getString("id"));
                                JSONObject jsonAlbum = jsonTR.getJSONObject("album");


                                tr.setName(jsonAlbum.getString("name"));

                                JSONArray jsonImgs = jsonAlbum.getJSONArray("images");
                                JSONObject jsonImg = jsonImgs.getJSONObject(0);
                                tr.setUrlImage(jsonImg.getString("url"));

                                JSONArray jsonArtistes = jsonAlbum.getJSONArray("artists");
                                JSONObject jsonArtiste = jsonArtistes.getJSONObject(0);
                                tr.setArtiste(jsonArtiste.getString("name"));


                                lesTRs.add(tr);

                            }

                            for(Track tr : lesTRs) {
                                new TrackActivity.DownLoadImageTask(TrackActivity.this.grille, tr).execute();
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    public void Back(View v) {
        finish() ;
    }

    private class DownLoadImageTask extends AsyncTask<String,Void, Bitmap> {
        ImageView imageView;
        GridLayout grille;
        Track tr;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        public DownLoadImageTask(GridLayout grille, Track tr) {
            this.grille = grille;
            this.tr = tr;
        }


        protected Bitmap doInBackground(String...urls){
            String urlOfImage = tr.getUrlImage() ;
            Bitmap logo = null;
            try{
                InputStream is = new URL(urlOfImage).openStream();

                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){
                e.printStackTrace();
            }
            return logo;
        }

        protected void onPostExecute(Bitmap result){
            LinearLayout linearLayout = new LinearLayout(TrackActivity.this);
            ImageView imgTR = new ImageView(TrackActivity.this);
            imgTR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Bundle bdl = new Bundle() ;
                    bdl.putSerializable( "music" , tr );
                    Intent intent = new Intent(TrackActivity.this, ListenActivity.class);
                    intent.putExtras( bdl ) ;
                    startActivity(intent);

                }
            });
            imgTR.setImageBitmap(result);
            linearLayout.addView(imgTR);
            TextView textGenre = new TextView(TrackActivity.this);
            textGenre.setText(tr.getName());
            textGenre.setTextColor(Color.BLACK);
            LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(textGenre, paramText);
            grille.addView(linearLayout);

        }
    }
}
