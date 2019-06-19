package com.example.loginapp.data.sync_network;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {

    private static final String BASE_URL = "http://jsonplaceholder.typicode.com/";

    private Retrofit retrofit;

    private static NetworkUtils networkUtils;

    private static final Object LOCK = new Object();

    private NetworkUtils(){
        this.retrofit = initRetrofit();
    }

    public static NetworkUtils getInstance(){
        if (networkUtils == null){
            synchronized (LOCK){
                networkUtils = new NetworkUtils();
            }
        }
        return networkUtils;
    }

    private Retrofit initRetrofit() {
        if (retrofit == null){
            synchronized (LOCK){
                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return retrofit;
    }

    public JsonPlaceHolderApi getJsonPlaceHolderApi() {
        return retrofit.create(JsonPlaceHolderApi.class);
    }
}