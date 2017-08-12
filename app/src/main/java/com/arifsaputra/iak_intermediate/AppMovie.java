package com.arifsaputra.iak_intermediate;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Cache;
import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HurlStack;

/**
 * Created by Chyrus on 8/12/17.
 * Muh Arif Saputra (Android Developer)
 */

public class AppMovie extends Application{

    private static final String TAG = "AppMovie";

    private static AppMovie mInstance;

    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        //init class application
        mInstance = this;
    }

    //method manggil class application yang sudah jalan.
    public static synchronized AppMovie getInstance() {
        return mInstance;
    }

    protected boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }

    //region volley configuration
    //method ini buat ngambil request antrian volley
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            Cache cache = new DiskBasedCache(getApplicationContext().getCacheDir(), 10 * 1024 * 1024); // cache data 5 hari
            Network network = new BasicNetwork(new HurlStack());
            mRequestQueue = new RequestQueue(cache, network);
            mRequestQueue.start();
        }
        return mRequestQueue;
    }
    //method ini buat nambahin request volley pake tag custom
    public <T> void addToRequestQueue(Request<T> req, String tag) {
        Log.d("req_queue_volley", req + "|" + tag);
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }
    //method ini buat nambahin request volley pake tag default class application ini (AppMovie)
    public <T> void addToRequestQueue(Request<T> req) {
        Log.d("req_queue_volley", req + "");
        req.setTag(TAG);
        getRequestQueue().add(req);
    }
    //method ini buat cancel request volley nya
    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
    //endregion

}
