package com.example.rahmabouraoui.foxsoundi;

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

public class GenresImagesActivity extends AppCompatActivity {

    GridLayout grille;
    List<Genre> genres = new ArrayList<Genre>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres_images);

        mQueue = Volley.newRequestQueue(this);

        System.out.println("Genres");

        String url = "https://foxsoundi2.azurewebsites.net/v1/music/genre";
        System.out.println("1");
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject jsonCategories = response.getJSONObject("categories");
                            JSONArray jsonItems = jsonCategories.getJSONArray("items");

                            for (int i = 0; i < jsonItems.length(); i++) {
                                JSONObject jsonGenre = jsonItems.getJSONObject(i);
                                Genre genre = new Genre();
                                genre.setHref(jsonGenre.getString("href"));
                                genre.setId(jsonGenre.getString("id"));
                                genre.setName(jsonGenre.getString("name"));
                                JSONArray jsonIcones = jsonGenre.getJSONArray("icons");
                                JSONObject jsonIcone = jsonIcones.getJSONObject(0);
                                Icone icone = new Icone();
                                icone.setHeight(jsonIcone.getInt("height"));
                                icone.setUrl(jsonIcone.getString("url"));
                                icone.setWidth(jsonIcone.getInt("width"));
                                genre.setIcone(icone);
                                genres.add(genre);
                                Log.i("GENRE ; " , genre.toString());
                                System.out.println(genre.toString());

                            }

                            for(Genre genre : genres) {
                                new DownLoadImageTask(GenresImagesActivity.this.grille, genre).execute();
                                /*LinearLayout linearLayout = new LinearLayout(GenresImagesActivity.this);
                                TextView textGenre = new TextView(GenresImagesActivity.this);
                                textGenre.setText(genre.getName());
                                LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                linearLayout.addView(textGenre, paramText);
                                grille.addView(linearLayout);*/
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

    private class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        ImageView imageView;
        GridLayout grille;
        Genre genre;

        public DownLoadImageTask(ImageView imageView){
            this.imageView = imageView;
        }

        public DownLoadImageTask(GridLayout grille, Genre genre) {
            this.grille = grille;
            this.genre = genre;
        }

        /*
                            doInBackground(Params... params)
                                Override this method to perform a computation on a background thread.
                         */
        protected Bitmap doInBackground(String...urls){
            //String urlOfImage = urls[0];
            String urlOfImage = genre.getIcone().getUrl();
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
            LinearLayout linearLayout = new LinearLayout(GenresImagesActivity.this);
            ImageView imgGenre = new ImageView(GenresImagesActivity.this);
            imgGenre.setImageBitmap(result);
            linearLayout.addView(imgGenre);
            TextView textGenre = new TextView(GenresImagesActivity.this);
            textGenre.setText(genre.getName());
            LinearLayout.LayoutParams paramText = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            linearLayout.addView(textGenre, paramText);
            grille.addView(linearLayout);

        }
    }
}
