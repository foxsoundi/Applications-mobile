package com.example.rahmabouraoui.foxsoundi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class GenresActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_genres);

        mTextViewResult = findViewById(R.id.textViewJson);

        mQueue = Volley.newRequestQueue(this);

        String url = "https://foxsoundiapi.azurewebsites.net/v1/music/genres";

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
    }

    //String URL ="https://localhost:5000/v1/music/genres";

   /* @Override
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

    }*/

    public void Back(View v) {
        finish() ;
    }

}
