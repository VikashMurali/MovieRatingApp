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

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.FragmentHomePageBinding;
import com.example.movieratingapp.viewmodel.HomePageFragmentViewModel;



public class HomePageFragment extends Fragment {

    FragmentHomePageBinding fragmentHomePageBinding;
    HomePageFragmentViewModel homePageFragmentViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentHomePageBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home_page, null, false);

        homePageFragmentViewModel = new HomePageFragmentViewModel(getContext());
        fragmentHomePageBinding.recyclerview.setLayoutManager(new LinearLayoutManager(container.getContext()));
        fragmentHomePageBinding.recyclerview.setAdapter(homePageFragmentViewModel.setAdapter());



        View view = fragmentHomePageBinding.getRoot();
        return view;
    }







}