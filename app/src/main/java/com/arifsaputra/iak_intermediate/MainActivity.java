package com.arifsaputra.iak_intermediate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private String url_movie = "https://api.themoviedb.org/3/movie/550?api_key=d2d9ab691917e86e312e24cb610bdc9a";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isInternetConnectionAvailable())
            getData();
        else
            Toast.makeText(this, "ga ada koneksi", Toast.LENGTH_SHORT).show();
    }

    private void getData() {
        //init set request api
        StringRequest request = new StringRequest(Request.Method.GET //method request (POST,GET,DELETE,PUT)
                , url_movie //url api
                , new Response.Listener<String>() { //response api
            @Override
            public void onResponse(String response) {//response string butuh convert ke json

                try {
                    //convert string ke json
                    JSONObject parent = new JSONObject(response);

                    //contoh buat ambil data object json
                    Log.d(TAG, "onResponse: backdrop_path = " + parent.getString("backdrop_path"));
                    Log.d(TAG, "onResponse: budget = " + parent.getInt("budget"));

                    //contoh buat ambil data array
                    JSONArray production_companies = parent.getJSONArray("production_companies");

                    for (int i = 0; i < production_companies.length(); i++) {
                        JSONObject anak_prod = production_companies.getJSONObject(i);

                        Log.d(TAG, "onResponse: id = "+anak_prod.getInt("id"));
                        Log.d(TAG, "onResponse: name = "+anak_prod.getString("name"));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { // error response
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        //add to que request on appMovie
        AppMovie.getInstance().addToRequestQueue(request, TAG);
    }
}
