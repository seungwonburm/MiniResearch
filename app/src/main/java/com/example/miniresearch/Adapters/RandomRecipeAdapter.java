package com.example.miniresearch.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.miniresearch.Models.Recipe;
import com.example.miniresearch.R;
import com.squareup.picasso.Picasso;

import java.util.List;


public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeViewHolder>{   //Adapter to present our information that we collect form api
    Context context;
    List<Recipe> list;

    public RandomRecipeAdapter(Context context, List<Recipe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RandomRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_random_recipe, parent, false));   //When creating the view, we inflate from context
    }

    @Override
    public void onBindViewHolder(@NonNull RandomRecipeViewHolder holder, int position) { //This extracts the neccessary information from the api (title of recipe, likes, serving, and time.
        holder.textView_title.setText(list.get(position).title);
        holder.textView_title.setSelected(true);
        holder.textView_likes.setText(list.get(position).aggregateLikes+" Likes");
        holder.textView_servings.setText(list.get(position).servings+" Servings");
        holder.textView_time.setText(list.get(position).readyInMinutes+ " Minutes");
        Picasso.get().load(list.get(position).image).into(holder.imageView_food); //We use picasso to display image in our app
    }

    @Override
    public int getItemCount() { //function to get the number of items in our list
        return list.size();
    }
}

class RandomRecipeViewHolder extends RecyclerView.ViewHolder {  //This is a recycler view to show all the recipes we fetch
    CardView random_list_container;  //CardView for our overall view of container
    TextView textView_title, textView_servings, textView_likes, textView_time; //All info extracted from api will be in TextView format
    ImageView imageView_food; //The image of food displayed using picasso will be type ImageView


    public RandomRecipeViewHolder(@NonNull View itemView) { //all code below is responsible for storing the values into our variabless below
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_time = itemView.findViewById(R.id.textView_time);
        imageView_food = itemView.findViewById(R.id.imageView_food);


    }
}