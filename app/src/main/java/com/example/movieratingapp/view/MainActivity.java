package com.example.movieratingapp.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.ActivityMainBinding;
import com.example.movieratingapp.fragments.DescriptionFragment;
import com.example.movieratingapp.fragments.HomePageFragment;


public class MainActivity extends AppCompatActivity {
    ActivityMainBinding mainBinding;

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        if(isNetworkConnected()==true) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.frameLayout, new HomePageFragment())
                    .commit();
        }
        else{
            Toast.makeText(this, "Please connect to a network", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isNetworkConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
