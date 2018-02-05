package com.innoplexus.rohitassignment_04_02_2018;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.innoplexus.rohitassignment_04_02_2018.adapters.ContactAdapter;
import com.innoplexus.rohitassignment_04_02_2018.model.Example;
import com.innoplexus.rohitassignment_04_02_2018.retrofit_utils.RetrofitUtils;
import com.innoplexus.rohitassignment_04_02_2018.retrofit_utils.restUtils.RestCallInterface;
import com.innoplexus.rohitassignment_04_02_2018.utils.InternetUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by Rohit K. Pawar on 04-Feb-18.
 */
public class MainActivity extends BaseActivity {
    private Toolbar toolbar;
    private Context mContext;
    private RecyclerView recyclerView;
    private Retrofit retrofit;
    private ArrayList<Example> exampleList = new ArrayList<>();
    private LinearLayoutManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mContext = MainActivity.this;
        retrofit = RetrofitUtils.getRetrofit();
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        manager = new LinearLayoutManager(mContext, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        if (!InternetUtils.isInternetAvailable(mContext)) {
            InternetUtils.showNoInternetDialog(mContext);
        } else {
            getContacts();
        }
    }

    private void getContacts() {
        showProgress();
        //Creating Rest Services
        RestCallInterface restInterface = retrofit.create(RestCallInterface.class);
        //Calling method to get whether report
        Call<List<Example>> call = restInterface.getAllContacts();
        call.enqueue(new Callback<List<Example>>() {
            @Override
            public void onResponse(Call<List<Example>> call, Response<List<Example>> response) {
                hideProgress();
                if (response.isSuccessful()) {
                    exampleList = (ArrayList<Example>) response.body();
                    if (exampleList != null && !exampleList.isEmpty()) {
                        setVerticalContactView();
                    } else {
                        Toast.makeText(mContext, "Contact not available", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Example>> call, Throwable t) {
                hideProgress();
                t.printStackTrace();
                Toast.makeText(mContext, "" + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setVerticalContactView() {
        ContactAdapter contactAdapter = new ContactAdapter(mContext, exampleList);
        recyclerView.setAdapter(contactAdapter);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Exit app")
                .setMessage("Are you sure you want to exit app?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }

                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }

                })
                .show();
    }
}
