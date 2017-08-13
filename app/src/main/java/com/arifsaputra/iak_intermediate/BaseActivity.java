package com.arifsaputra.iak_intermediate;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Chyrus on 8/12/17.
 * Muh Arif Saputra (Android Developer)
 */

public class BaseActivity extends AppCompatActivity {

    //loading
    private ProgressDialog pDialog;
    //alert
    private AlertDialog.Builder alert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init dialog
        pDialog = new ProgressDialog(this);
        //init alert
        alert = new AlertDialog.Builder(this);
    }

    //method buat alert popup
    protected void showAlertMessage(String title,String message) {
        alert.setTitle(title);
        alert.setMessage(message);
        alert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {}
        });
        alert.show();
    }

    protected void showDialog(String msg) {
        pDialog.setMessage(msg);// buat set pesan loading
        pDialog.setCancelable(false);// buat set loading gk bisa di cancel
        pDialog.show(); // buat nampilin loading nya
    }

    //buat ngilangin loading
    protected void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    //ketika app kita close, pDialog nya harus di close juga
    @Override
    protected void onDestroy() { //method yang jalan saat aplikasi close
        super.onDestroy();
        if (pDialog != null)
            pDialog.cancel();
    }

    //method pengecekan koneksi internet
    protected boolean isInternetConnectionAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork == null ? false : activeNetwork.isConnectedOrConnecting();
    }

}
