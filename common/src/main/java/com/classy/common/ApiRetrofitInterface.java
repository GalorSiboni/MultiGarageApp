package com.classy.common;

import com.classy.common.model.Garage;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiRetrofitInterface {


    @GET("/raw/WypPzJCt")
    Call<Garage> getGarage();

}