package com.arifsaputra.iak_intermediate.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    public MovieViewHolder(View itemView) {
        super(itemView);

        title = (TextView) itemView.findViewById(R.id.title);
        vote_average = (TextView) itemView.findViewById(R.id.vote_average);
        release_date = (TextView) itemView.findViewById(R.id.release_date);
    }
}
