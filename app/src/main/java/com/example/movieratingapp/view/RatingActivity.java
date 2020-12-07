package com.example.movieratingapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.ActivityRatingBinding;
import com.example.movieratingapp.fragments.HomePageFragment;

public class RatingActivity extends AppCompatActivity {
    int position;
    ActivityRatingBinding activityRatingBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityRatingBinding = DataBindingUtil.setContentView(this,R.layout.activity_rating);

        String episodeNumber = getIntent().getStringExtra("episode");
        position = getIntent().getIntExtra("position",0);
        activityRatingBinding.ep.setText("Did you enjoy watching Episode : "+episodeNumber+" ? Rate the episode here:");

    }

    @Override
    protected void onStop() {

        super.onStop();
        finish();
    }

    @Override
    public void onBackPressed() {
        float ratingValue = activityRatingBinding.ratingBar2.getRating();
        SharedPreferences.Editor editor = getSharedPreferences("mySharedPreference", MODE_PRIVATE).edit();

        editor.putFloat("rating_value"+position,ratingValue);
        editor.apply();

        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);

    }
}