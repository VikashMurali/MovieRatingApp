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
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardViewBinding cardViewBinding = DataBindingUtil.inflate(layoutInflater, R.layout.card_view,parent,false);
        return new AdapterViewHolder(cardViewBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterViewHolder holder, int position) {

        holder.cardViewBinding.name.setText(dataHolder.get(position).name);
        holder.cardViewBinding.season.setText(String.valueOf(dataHolder.get(position).season));
        holder.cardViewBinding.runtime.setText(String.valueOf(dataHolder.get(position).runtime)+" minutes");
        holder.cardViewBinding.number.setText(String.valueOf(dataHolder.get(position).number));
        Picasso.get().load(dataHolder.get(position).image.medium).into(holder.cardViewBinding.imageView);

        SharedPreferences preference = context.getSharedPreferences("mySharedPreference", MODE_PRIVATE);
        float itemRatingValue = preference.getFloat("rating_value"+position,0);

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

                bundle.putString("Name", dataHolder.get(position).getName());
                bundle.putInt("Season", dataHolder.get(position).getSeason());
                bundle.putInt("Number", dataHolder.get(position).getNumber());
                bundle.putInt("RunTime", dataHolder.get(position).getRuntime());
                bundle.putString("Summary", dataHolder.get(position).getSummary());
                bundle.putString("ImageUrl", dataHolder.get(position).getImage().getOriginal());

                DescriptionFragment descriptionFragment = new DescriptionFragment();

                descriptionFragment.setArguments(bundle);
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, descriptionFragment).addToBackStack(null).commit();
            }


        }));
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
