package com.example.movieratingapp.services;



import com.example.movieratingapp.model.ModelClass;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {
    String BASE_URL = "https://api.tvmaze.com/shows/11/";

    @GET("episodes")
    Observable<List<ModelClass>> getMoviesList();
    //Call<List<ModelClass>> getMoviesList();

}
