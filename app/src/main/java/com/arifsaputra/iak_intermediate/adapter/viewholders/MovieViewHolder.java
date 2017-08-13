package com.arifsaputra.iak_intermediate.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arifsaputra.iak_intermediate.R;

/**
 * Created by Chyrus on 8/13/17.
 * Muh Arif Saputra (Android Developer)
 */

public class MovieViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView vote_average;
    public TextView release_date;
    public ImageView gambar_movie;
    public LinearLayout layout_parent;

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        vote_average = (TextView) itemView.findViewById(R.id.vote_average);
        release_date = (TextView) itemView.findViewById(R.id.release_date);
        gambar_movie = (ImageView) itemView.findViewById(R.id.gambar_movie);
        layout_parent = (LinearLayout) itemView.findViewById(R.id.layout_parent);
    }

    public LinearLayout getItem() {
        return layout_parent;
    }
}
