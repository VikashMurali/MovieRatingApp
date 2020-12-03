package com.example.movieratingapp.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.FragmentDescriptionBinding;
import com.squareup.picasso.Picasso;


public class DescriptionFragment extends Fragment {
    FragmentDescriptionBinding fragmentDescriptionBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDescriptionBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_description, container, false);
        View view = fragmentDescriptionBinding.getRoot();

        Bundle bundle = this.getArguments();

        String name = bundle.getString("Name");
        String season = bundle.getString("Season");
        String number = bundle.getString("Number");
        String runTime = bundle.getString("RunTime");
        String summary = bundle.getString("Summary");
        String image = bundle.getString("ImageUrl");

        String s1 = "Season : " + season;
        String s2 = "Episode : " + number;
        String s3 = "Runtime : "+runTime + " Minutes";

        fragmentDescriptionBinding.descriptionName.setText(name);
        fragmentDescriptionBinding.descriptionSeason.setText(s1);
        fragmentDescriptionBinding.descriptionEpisode.setText(s2);
        fragmentDescriptionBinding.descriptionRuntime.setText(s3);
        fragmentDescriptionBinding.description.setText(summary);
        Picasso.get().load(image).into(fragmentDescriptionBinding.largeimage);

        return view;
    }



}