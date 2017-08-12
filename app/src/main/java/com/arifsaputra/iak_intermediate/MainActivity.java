package com.arifsaputra.iak_intermediate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private String url_movie = "https://api.themoviedb.org/3/movie/550?api_key=d2d9ab691917e86e312e24cb610bdc9a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StringRequest request = new StringRequest(Request.Method.GET //method request (POST,GET,DELETE,PUT)
                , url_movie //url api
                , new Response.Listener<String>() { //response api
            @Override
            public void onResponse(String response) {


            }
        }, new Response.ErrorListener() { // error response
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        //add to que request on appMovie
        AppMovie.getInstance().addToRequestQueue(request,TAG);
    }
}
