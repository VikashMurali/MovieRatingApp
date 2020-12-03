package com.example.movieratingapp.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.util.Log;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.movieratingapp.adapter.RecyclerViewAdapter;
import com.example.movieratingapp.model.ModelClass;
import com.example.movieratingapp.services.ApiClient;
import com.example.movieratingapp.services.ApiService;

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.FragmentHomePageBinding;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomePageFragment extends Fragment implements LifecycleObserver {

    List<ModelClass> moviesList;
    FragmentHomePageBinding fragmentHomePageBinding;
    private ProgressDialog progressDialog;


 @Override
    public void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
    }
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomePageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, null, false);
        View view = fragmentHomePageBinding.getRoot();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        fragmentHomePageBinding.recyclerview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        sharedPreferences = getContext().getSharedPreferences("MOVIES", Context.MODE_PRIVATE);
         editor = sharedPreferences.edit();



        String prefs = sharedPreferences.getString("MOVIES_LIST", null);
        if(prefs == null) {
            MakeApiCall();
        }
        else {
            callAdapter(getSharedPrefs(prefs));
        }
        return view;
    }

    private void MakeApiCall() {
        ApiService apiService = ApiClient.getApiService();
        Call<List<ModelClass>> call = apiService.getMoviesList();
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                moviesList = response.body();
                storeSharedPrefs(moviesList);
                callAdapter(moviesList);

            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {
                progressDialog.hide();
                Toast.makeText(getContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
                Log.d("Failure",""+t.getMessage());
            }
        });
    }
    private void storeSharedPrefs(List<ModelClass> moviesList){
        Gson gson = new Gson();
        String json = gson.toJson(moviesList);
        editor.putString("MOVIES_LIST", json);
        editor.commit();
    }
    private void callAdapter(List<ModelClass> list){
        RecyclerViewAdapter movieAdapter = new RecyclerViewAdapter(getContext(),list);
        fragmentHomePageBinding.recyclerview.setAdapter(movieAdapter);
        progressDialog.hide();
    }


    private List<ModelClass> getSharedPrefs(String prefs){
        List<ModelClass> moviesList;

        Gson gson = new Gson();
        Type type = new TypeToken<List<ModelClass>>(){}.getType();

        moviesList = gson.fromJson(prefs, type);

        return moviesList;
    }


}