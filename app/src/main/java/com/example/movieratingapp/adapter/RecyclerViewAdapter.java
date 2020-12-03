package com.example.movieratingapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movieratingapp.fragments.DescriptionFragment;
import com.example.movieratingapp.model.ModelClass;
import com.example.movieratingapp.R;
import com.example.movieratingapp.databinding.CardViewBinding;
import com.example.movieratingapp.view.RatingActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.AdapterViewHolder> {

    private Context context;
    private List<ModelClass> dataHolder;


    public RecyclerViewAdapter(Context context,List<ModelClass> modelClassList) {
        this.context = context;
        this.dataHolder = modelClassList;
    }


    @NonNull
    @Override
    public AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardViewBinding cardViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.card_view, parent, false);
        return new AdapterViewHolder(cardViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        ModelClass modelClass = dataHolder.get(position);
        float itemRatingValue = getItemRatingValueFromSharedPreference(position);


        if(itemRatingValue > 0) {
            holder.cardViewBinding.ratingBar.setRating(itemRatingValue);
            holder.cardViewBinding.ratingBar.setVisibility(View.VISIBLE);
        }
        else{
            holder.cardViewBinding.ratingBar.setVisibility(View.GONE);
        }



        holder.cardViewBinding.cardView.setOnClickListener((view -> {

            int count = getValueFromSharedPreference(position);
            SharedPreferences.Editor editor = context.getSharedPreferences("mySharedPreference", MODE_PRIVATE).edit();
            count++;
            editor.putInt(String.valueOf(position), count);
            editor.apply();

            if(count%5==0) {

                Intent intent = new Intent(view.getContext(), RatingActivity.class);
                intent.putExtra("episode",String.valueOf(dataHolder.get(position).number));
                intent.putExtra("position",position);
                (view.getContext()).startActivity(intent);
            }
            else {

                Bundle bundle = new Bundle();

                AppCompatActivity activity = (AppCompatActivity) view.getContext();

                bundle.putString("Name", modelClass.getName());
                bundle.putString("Season", modelClass.getSeason());
                bundle.putString("Number", modelClass.getNumber());
                bundle.putString("RunTime", modelClass.getRuntime());
                bundle.putString("Summary", modelClass.getSummary());
                bundle.putString("ImageUrl", modelClass.getImage().getOriginal());

                DescriptionFragment descriptionFragment = new DescriptionFragment();

                descriptionFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, descriptionFragment).addToBackStack(null).commit();
            }
        }));
        holder.cardViewBinding.setItem(modelClass);
    }

    private float getItemRatingValueFromSharedPreference(int position) {
        SharedPreferences preference = context.getSharedPreferences("mySharedPreference", MODE_PRIVATE);
        return preference.getFloat("rating_value"+position,0);
    }


    public int getValueFromSharedPreference(int position_value){
    SharedPreferences prefs = context.getSharedPreferences("mySharedPreference", MODE_PRIVATE);
    return prefs.getInt(String.valueOf(position_value),0);
}

    @Override
    public int getItemCount() {
        return dataHolder.size();
    }

    public class AdapterViewHolder extends RecyclerView.ViewHolder{
        private CardViewBinding cardViewBinding;
        public AdapterViewHolder(@NonNull CardViewBinding cardViewBinding) {
            super(cardViewBinding.getRoot());
            this.cardViewBinding = cardViewBinding;
        }

    }
}
