package com.example.movieratingapp.services;



import com.example.movieratingapp.model.ModelClass;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;
public interface ApiService {
    String BASE_URL = "http://api.tvmaze.com/shows/11/";

    @GET("episodes")
    Call<List<ModelClass>> getMoviesList();

}
