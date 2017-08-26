package com.arifsaputra.iak_intermediate.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.arifsaputra.iak_intermediate.BaseActivity;
import com.arifsaputra.iak_intermediate.R;
import com.arifsaputra.iak_intermediate.model.Movies;

public class DetailMovieActivity extends BaseActivity {

    private Movies movies;
    private TextView overview;
    private Button bt_favorite;
    private Button bt_delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        //receive data from movies
        movies = (Movies) getIntent().getSerializableExtra("movie");

        //bind view
        overview = (TextView) findViewById(R.id.overview);

        overview.setText(movies.getOriginal_title());

        bt_favorite = (Button) findViewById(R.id.bt_favorite);
        bt_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movies.setFavorite("true");
                getDB().updateMovie(movies);
                finish();
            }
        });
        bt_delete = (Button) findViewById(R.id.bt_delete);
        bt_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDB().deleteMovie(movies);
                finish();
            }
        });
    }


}
