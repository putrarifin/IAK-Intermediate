package com.arifsaputra.iak_intermediate.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.arifsaputra.iak_intermediate.BaseActivity;
import com.arifsaputra.iak_intermediate.R;
import com.arifsaputra.iak_intermediate.model.Movies;

public class DetailMovieActivity extends BaseActivity {

    private Movies movies;
    private TextView overview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        //receive data from movies
        movies = (Movies) getIntent().getSerializableExtra("movie");

        //bind view
        overview = (TextView) findViewById(R.id.overview);


        overview.setText(movies.getOverview());
    }


}
