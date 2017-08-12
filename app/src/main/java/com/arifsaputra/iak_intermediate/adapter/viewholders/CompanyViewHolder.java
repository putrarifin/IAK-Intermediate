package com.arifsaputra.iak_intermediate.adapter.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.arifsaputra.iak_intermediate.R;

/**
 * Created by Chyrus on 8/12/17.
 * Muh Arif Saputra (Android Developer)
 */

public class CompanyViewHolder extends RecyclerView.ViewHolder {

    public TextView nama;

    public CompanyViewHolder(View itemView) {
        super(itemView);
        nama = (TextView) itemView.findViewById(R.id.nama_company);
    }

}
