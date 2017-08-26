package com.arifsaputra.iak_intermediate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arifsaputra.iak_intermediate.BaseActivity;
import com.arifsaputra.iak_intermediate.R;
import com.arifsaputra.iak_intermediate.URLs;
import com.arifsaputra.iak_intermediate.adapter.ListAdapter;
import com.arifsaputra.iak_intermediate.adapter.viewholders.MovieViewHolder;
import com.arifsaputra.iak_intermediate.app.AppMovie;
import com.arifsaputra.iak_intermediate.model.Movies;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MovieActivity extends BaseActivity {

    private static final String TAG = "MovieActivity";

    private RecyclerView recyclerView;
    private ListAdapter listAdapter;
    private ArrayList<Movies> list = new ArrayList<>();

    private Button bt_read;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initRecycler();
        initAdapterMovies();

        showDialog("Loading...");

        if (isInternetConnectionAvailable()) {
//            getData(URLs.URL_MOVIE_TOP_RATED);
            getData(URLs.URL_MOVIE_POPULAR);
        } else {
            Toast.makeText(this, "no connection", Toast.LENGTH_SHORT).show();
            hideDialog();
        }

        bt_read = (Button) findViewById(R.id.bt_read_movies_local);

        bt_read.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Reading all movies
                Log.d("Reading: ", "Reading all movies..");
                List<Movies> movies = getDB().getAllMovies();

                for (Movies mv : movies) {
                    String log = "Id: " + mv.getId() + " ,Title: " + mv.getOriginal_title() + " ,favorite: " + mv.getFavorite();
                    // Writing Movies to log
                    Log.d("movie : ", log);
                }
            }
        });
    }

    private void initRecycler() {
        // bind recyclerview from layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //set layout orientation (Linear/GRID)
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2)); //2 itu jumlah column

        //menambahkan divider peritem
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
    }

    private void initAdapterMovies() {
        //setting adapter generic class <class_object,viewholder>(item_list,viewholder.class,object.class,list)
        listAdapter = new ListAdapter<Movies//object
                , MovieViewHolder> //view holder
                (R.layout.item_list_movie // layout item list
                        , MovieViewHolder.class // class View Holder
                        , Movies.class // class object
                        , list) {
            @Override
            protected void bindView(MovieViewHolder holder, final Movies model, int position) {

                //color background
//                if (position%2 == 0)
//                    holder.getItem().setBackgroundColor(ContextCompat.getColor(getApplicationContext(),android.R.color.holo_orange_light));
//                else
//                    holder.getItem().setBackgroundColor(ContextCompat.getColor(getApplicationContext(),android.R.color.holo_blue_bright));
                //glide
                Glide.with(getApplicationContext())
                        .load(URLs.BASE_IMAGE + model.getPoster_path()) // url nya
                        .into(holder.gambar_movie);//item view image nya
                /*
                //picasso
                Picasso.with(getApplicationContext())
                        .load(URLs.BASE_IMAGE+model.getPoster_path()) // url nya
                        .into(holder.gambar_movie);//item view image nya*/

                holder.title.setText(model.getOriginal_title());
                //parsing to String
                holder.vote_average.setText(String.valueOf(model.getVote_average()));
                holder.release_date.setText(model.getRelease_date());

                holder.getItem().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        Toast.makeText(MovieActivity.this, model.getOriginal_title(), Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(MovieActivity.this, DetailMovieActivity.class);
                        in.putExtra("movie", model);

                        startActivity(in);
                    }
                });
            }
        };

        recyclerView.setAdapter(listAdapter);
    }

    public void getData(String url) {
        //init set request api
        StringRequest request = new StringRequest(Request.Method.GET //method request (POST,GET,DELETE,PUT)
                , url  //url api
                , new Response.Listener<String>() { //response api
            @Override
            public void onResponse(String response) {//response string butuh convert ke json

                try {
                    //convert string ke json
                    JSONObject parent = new JSONObject(response);

                    //contoh buat ambil data array
                    JSONArray results = parent.getJSONArray("results");
                    //kosongin list company
                    list = new ArrayList<>();
                    getDB().clearMovie();
                    for (int i = 0; i < results.length(); i++) {
                        JSONObject anak_results = results.getJSONObject(i);

                        Movies m = new Movies();
                        m.setId(anak_results.getInt("id"));
                        m.setOriginal_language(anak_results.getString("original_language"));
                        m.setOriginal_title(anak_results.getString("original_title"));
                        m.setOverview(anak_results.getString("overview"));
                        m.setPopularity(anak_results.getDouble("popularity"));
                        m.setPoster_path(anak_results.getString("poster_path"));
                        m.setRelease_date(anak_results.getString("release_date"));
                        m.setVote_average(anak_results.getDouble("vote_average"));
                        m.setVote_count(anak_results.getInt("vote_count"));

                        //insert to db local SQLITE
                        if (getDB().getCountMovies() != results.length()) {
                            getDB().addMovie(m);
                        }


                        list.add(m);
                    }

                    listAdapter.swapData(list);
                    hideDialog();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() { // error response
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                hideDialog();
            }
        });

        //add to que request on appMovie
        AppMovie.getInstance().addToRequestQueue(request, TAG);
    }
}
