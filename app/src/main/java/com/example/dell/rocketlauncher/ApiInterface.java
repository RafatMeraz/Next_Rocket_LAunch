package com.example.dell.rocketlauncher;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("lunch/top_launch")
    Call<ApiResponse> getTopRatedMovies(@Query("TWZA75za4y5AsHWnbGqZngoTvgvHEgpIEmKUYwHO") String apiKey);

    @GET("lunch/{id}")
    Call<ApiResponse> getMovieDetails(@Path("id") int id, @Query("TWZA75za4y5AsHWnbGqZngoTvgvHEgpIEmKUYwHO") String apiKey);

}
