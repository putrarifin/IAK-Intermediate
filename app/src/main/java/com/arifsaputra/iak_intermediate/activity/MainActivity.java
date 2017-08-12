package com.arifsaputra.iak_intermediate.activity;

import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.arifsaputra.iak_intermediate.BaseActivity;
import com.arifsaputra.iak_intermediate.R;
import com.arifsaputra.iak_intermediate.adapter.ListAdapter;
import com.arifsaputra.iak_intermediate.adapter.viewholders.CompanyViewHolder;
import com.arifsaputra.iak_intermediate.app.AppMovie;
import com.arifsaputra.iak_intermediate.model.ProductionCompanies;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    private String url_movie = "https://api.themoviedb.org/3/movie/550?api_key=d2d9ab691917e86e312e24cb610bdc9a";

    private RecyclerView recyclerView;

    private ListAdapter listAdapter;

    //buat simpan list company
    private ArrayList<ProductionCompanies> listCompany = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initRecycler();
        initAdapterCompany();
        if (isInternetConnectionAvailable())
            getData();
        else
            Toast.makeText(this, "ga ada koneksi", Toast.LENGTH_SHORT).show();
    }

    private void initRecycler() {
        // bind recyclerview from layout
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //set layout orientation (Linear/GRID)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //menambahkan divider peritem
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
    }

    private void initAdapterCompany() {
        //setting adapter generic class <class_object,viewholder>(item_list,viewholder.class,object.class,list)
        listAdapter = new ListAdapter<ProductionCompanies//object
                , CompanyViewHolder> //view holder
                (R.layout.item_list // layout item list
                        , CompanyViewHolder.class // class View Holder
                        , ProductionCompanies.class // class object
                        , listCompany) {// list company
            @Override
            protected void bindView(CompanyViewHolder holder, ProductionCompanies model, int position) {
                holder.nama.setText(model.getName());
            }
        };

        recyclerView.setAdapter(listAdapter);
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
                    //kosongin list company
                    listCompany = new ArrayList<>();

                    for (int i = 0; i < production_companies.length(); i++) {
                        JSONObject anak_prod = production_companies.getJSONObject(i);

                        Log.d(TAG, "onResponse: id = " + anak_prod.getInt("id"));
                        Log.d(TAG, "onResponse: name = " + anak_prod.getString("name"));

                        ProductionCompanies companies = new ProductionCompanies(anak_prod.getInt("id")
                                , anak_prod.getString("name"));

                        listCompany.add(companies);
                    }

                    listAdapter.swapData(listCompany);
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
