package com.example.movieratingapp.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.ActivityMainBinding;
import com.example.movieratingapp.fragments.DescriptionFragment;
import com.example.movieratingapp.fragments.HomePageFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);


        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.frameLayout,new HomePageFragment())
                .commit();
    }

}
