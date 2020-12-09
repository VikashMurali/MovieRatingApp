package com.example.movieratingapp.viewmodel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.movieratingapp.adapter.RecyclerViewAdapter;
import com.example.movieratingapp.model.ModelClass;
import com.example.movieratingapp.services.ApiClient;
import com.example.movieratingapp.services.ApiService;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class HomePageFragmentViewModel {
    Context context;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    RecyclerViewAdapter recyclerViewAdapter;
    ProgressDialog progressDialog;
    List<ModelClass> moviesList;

    public HomePageFragmentViewModel(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("MOVIES", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
    }

    public RecyclerViewAdapter setAdapter(){
        String prefs = sharedPreferences.getString("MOVIES_LIST", null);
        if (prefs == null){
            ApiService apiService = ApiClient.getApiService();

            Observable<List<ModelClass>> call = apiService
                    .getMoviesList()
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());

            call.subscribe(new Observer<List<ModelClass>>() {
                @Override
                public void onSubscribe(Disposable d) {}

                @Override
                public void onNext(List<ModelClass> movies) {
                    moviesList = movies;
                    progressDialog.hide();
                    recyclerViewAdapter = new RecyclerViewAdapter(context, moviesList);
                    storeSharedPrefs(moviesList);
                }


                @Override
                public void onError(Throwable e) { Toast.makeText(context, e.getMessage(), Toast.LENGTH_LONG).show(); }

                @Override
                public void onComplete() {
                }
            });
        } else {
            progressDialog.hide();
            recyclerViewAdapter = new RecyclerViewAdapter(context, getSharedPrefs(prefs));
        }
        return recyclerViewAdapter;
    }
    private void storeSharedPrefs(List<ModelClass> moviesList){
        Gson gson = new Gson();
        String json = gson.toJson(moviesList);
        editor.putString("MOVIES_LIST", json);
        editor.commit();
    }
    private List<ModelClass> getSharedPrefs(String prefs){
        List<ModelClass> moviesList;

        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelClass>>(){}.getType();

        moviesList = gson.fromJson(prefs, type);

        return moviesList;
    }
}
