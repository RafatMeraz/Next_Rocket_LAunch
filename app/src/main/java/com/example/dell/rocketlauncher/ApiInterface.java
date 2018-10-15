package com.example.dell.rocketlauncher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("movie/top_rated")
    Call<ApiResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<ApiResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);

}
