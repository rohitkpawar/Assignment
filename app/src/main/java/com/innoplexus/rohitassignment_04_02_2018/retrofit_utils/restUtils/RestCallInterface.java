package com.innoplexus.rohitassignment_04_02_2018.retrofit_utils.restUtils;

import com.innoplexus.rohitassignment_04_02_2018.model.Example;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Rohit K. Pawar on 04-Feb-18.
 */
public interface RestCallInterface {

    @GET("users")
    Call<List<Example>> getAllContacts();
}