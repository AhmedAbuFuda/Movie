package com.example.movie.datasource.remote;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static Retrofit getInstance(){
       return new Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
               .addConverterFactory(GsonConverterFactory.create()).build();
    }

    public static IMovieAPI cteareAPI(){
        return getInstance().create(IMovieAPI.class);
    }
}
