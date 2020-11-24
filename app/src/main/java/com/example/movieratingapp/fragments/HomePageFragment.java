package com.example.movieratingapp.fragments;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleObserver;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieratingapp.adapter.RecyclerViewAdapter;
import com.example.movieratingapp.model.ModelClass;
import com.example.movieratingapp.services.ApiClient;
import com.example.movieratingapp.services.ApiService;

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.FragmentHomePageBinding;

import java.io.InputStream;
import java.net.URL;
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomePageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, null, false);
        View view = fragmentHomePageBinding.getRoot();

        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Please Wait");
        progressDialog.show();
        fragmentHomePageBinding.recyclerview.setLayoutManager(new LinearLayoutManager(container.getContext()));

        ApiService apiService = ApiClient.getApiService();
        Call<List<ModelClass>> call = apiService.getMoviesList();
        call.enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                moviesList = response.body();

                RecyclerViewAdapter movieAdapter = new RecyclerViewAdapter(getContext(),moviesList);

                fragmentHomePageBinding.recyclerview.setAdapter(movieAdapter);
                progressDialog.hide();
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });

        return view;
    }




}