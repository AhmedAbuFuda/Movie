package com.example.movie.datasource.remote;

import com.example.movie.model.Example;
import com.example.movie.model.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface IMovieAPI {

    @GET("3/movie/popular")
    Call<Example> fetchMovies (@Query("api_key") String api_key );

}
