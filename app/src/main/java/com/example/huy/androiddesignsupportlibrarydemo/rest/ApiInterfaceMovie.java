package com.example.huy.androiddesignsupportlibrarydemo.rest;

import com.example.huy.androiddesignsupportlibrarydemo.data.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Huy on 5/14/2016.
 */
public interface ApiInterfaceMovie {
    @GET("movie/top_rated")
    Call<MovieResponse> getTopRatedMovies(@Query("api_key") String apiKey);

    @GET("movie/{id}")
    Call<MovieResponse> getMovieDetails(@Path("id") int id, @Query("api_key") String apiKey);
}
