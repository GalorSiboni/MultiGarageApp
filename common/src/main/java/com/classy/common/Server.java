package com.classy.common;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.classy.common.model.Garage;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Server {

    static final String BASE_URL = "https://pastebin.com";

    public static void getGarage(Context context, ApiGarageCallBack callBack) {
        //retrofit builder
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //instance for interface
        ApiRetrofitInterface httpRequestAPI = retrofit.create(ApiRetrofitInterface.class);

        Call<Garage> getGarage = httpRequestAPI.getGarage();
        getGarage.enqueue(new Callback<Garage>() {
            @Override
            public void onResponse(Call<Garage> call, Response<Garage> response) {
                if (response.isSuccessful()) {
                    // If login succeeded, display a message to the user.
                    Toast.makeText(context, "get garage succeeded",
                            Toast.LENGTH_SHORT).show();
                    callBack.onSuccess(response.body());

                } else
                    Toast.makeText(context, "something went wrong",
                            Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                // If login fails, display a message to the user.
                Toast.makeText(context, "get garage failed.",
                        Toast.LENGTH_SHORT).show();
                Log.d("getGarage", t.getMessage());
            }
        });
    }
}
