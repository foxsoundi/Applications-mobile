package com.example.rahmabouraoui.foxsoundi;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

import java.util.ArrayList;
import java.util.List;

public class GenresActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private List<Genre> genres = new ArrayList<>();
    private ListView lvGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);
        lvGenres = findViewById(R.id.lvGenres);

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

                        //ArrayAdapter<Genre> aaGenres = new ArrayAdapter<Genre>(GenresActivity.this, , genres);
                        ItemGenreAdapter aaItem = new ItemGenreAdapter();
                        GenresActivity.this.lvGenres.setAdapter(aaItem);


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

    //String URL ="https://localhost:5000/v1/music/genres";

   /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);



        String URL = "https://foxsoundiapi.azurewebsites.net/v1/music/genres";
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        final TextView textView = (TextView) findViewById(R.id.textViewJson);
        String text;

        JsonArrayRequest arrayRequest = new JsonArrayRequest(
            Request.Method.GET,
            URL,
            null,
            new Response.Listener<JSONArray>() {
                @Override
                public void onResponse(JSONArray response) {

                Log.e("Rest Response SUCCESS ", response.toString());

                    textView.setText(response.toString());
                }
            },
            new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Rest Response ERROR ", error.toString());
                    }
                }
        );
        requestQueue.add(arrayRequest);

    }

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        mTextViewResult = findViewById(R.id.textViewJson);

        mQueue = Volley.newRequestQueue(this);

        String url = "https://foxsoundi2.azurewebsites.net/v1/music/genre";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("categories");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject categories = jsonArray.getJSONObject(i);
                                String jurl = categories.getString("url");
                                mTextViewResult.append(jurl + "\n\n");
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
    }*/

    public void Back(View v) {
        finish() ;
    }

    class ItemGenreAdapter extends ArrayAdapter<Genre> {

        public ItemGenreAdapter() {
            super(GenresActivity.this, R.layout.list_item_genre, R.id.tvName, GenresActivity.this.genres);
        }


        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View vItem = super.getView(position, convertView, parent);
            TextView tvHeight = (TextView) findViewById(R.id.tvHeight);
            TextView tvWidth = (TextView) findViewById(R.id.tvWidth);
            Genre genre = GenresActivity.this.genres.get(position);
            tvHeight.setText(genre.getIcone().getHeight());
            tvWidth.setText(genre.getIcone().getWidth());
            return vItem;
        }
    }

}
