package com.example.dell.rocketlauncher;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClass {
    public static final String BASE_URL = "https://api.nasa.gov/planetary/apod?api_key=TWZA75za4y5AsHWnbGqZngoTvgvHEgpIEmKUYwHO";
    private static Retrofit retrofit = null;


    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
