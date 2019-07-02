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

        System.out.println("Tracks");

        String idPL = this.getIntent().getExtras().getString( "id" ) ;

        String url = "http://foxsoundi2.azurewebsites.net/v1/music/playlist/" + idPL + "/tracks" ;
        System.out.println("1 => " + url );
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Requete");
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
                                System.out.println( "Track >> " + jsonArtistes.length() );
                                JSONObject jsonArtiste = jsonArtistes.getJSONObject(0);
                                System.out.println( "      >> " + jsonArtiste.toString() );
                                tr.setArtiste(jsonArtiste.getString("name"));


                                lesTRs.add(tr);
                                Log.i("TR ; " , tr.toString());
                                System.out.println(tr.toString());

                            }

                            for(Track tr : lesTRs) {
                                System.out.println("TR $$$ : " + tr) ;
                                new TrackActivity.DownLoadImageTask(TrackActivity.this.grille, tr).execute();
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
        Track tr;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        public DownLoadImageTask(GridLayout grille, Track tr) {
            this.grille = grille;
            this.tr = tr;
        }

        /*
                            doInBackground(Params... params)
                                Override this method to perform a computation on a background thread.
                         */
        protected Bitmap doInBackground(String...urls){
            //String urlOfImage = urls[0];
            String urlOfImage = tr.getUrlImage() ;
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
            LinearLayout linearLayout = new LinearLayout(TrackActivity.this);
            //linearLayout.setGravity(LinearLayout.VERTICAL);
            ImageView imgTR = new ImageView(TrackActivity.this);
            imgTR.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println( "TR : " + tr ) ;

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
            LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(textGenre, paramText);
            grille.addView(linearLayout);

        }
    }
}
