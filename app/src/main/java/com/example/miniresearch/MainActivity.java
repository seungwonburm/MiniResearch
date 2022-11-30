package com.example.miniresearch;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import com.example.miniresearch.Adapters.RandomRecipeAdapter;
import com.example.miniresearch.Listeners.RandomRecipeResponseListener;
import com.example.miniresearch.Models.RandomRecipeAPIResponse;

public class MainActivity extends AppCompatActivity {
    ProgressDialog dialog; //Dialog message while api works on fetching information
    RequestManager manager; //Our request manager to request get information from api, the class will be explained in the next file
    RandomRecipeAdapter randomRecipeAdapter; //Adapter to get random recipe
    RecyclerView recyclerView; //RecyclerView to show all recipe in card format


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialog = new ProgressDialog(this); //on create we want to show a progress dialog as we fetch our api information
        dialog.setTitle("Presenting data to you by Yuchan, Jeet, Andrew, Lauren, please wait :)"); //this is the message we wanna show while user waits for info
        manager = new RequestManager(this); //we make our manager make a new request to our request manager in this context
        manager.getRandomRecipes(randomRecipeResponseListener); //we then ask manager to get random recipe and pass in a response listener to know if we did get information
        dialog.show(); //this shows the dialog
    }

    private final RandomRecipeResponseListener randomRecipeResponseListener = new RandomRecipeResponseListener() { //this is our responser listener that determines if we were successful in fetching information or not
        @Override
        public void didFetch(RandomRecipeAPIResponse response, String message) { //function in the case that we fetch the information
            dialog.dismiss(); //we dismiss the loading dialog
            recyclerView = findViewById(R.id.recycler_random); //we set our recycler view to be recycler_random that we generated
            recyclerView.setHasFixedSize(true);
            recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 1)); //This sets our layout in main activity
            randomRecipeAdapter = new RandomRecipeAdapter(MainActivity.this, response.recipes); //calling randomrecipeadapter to get random recipes and storing them in our variable
            recyclerView.setAdapter(randomRecipeAdapter); //settings the adapter of recyclerview
        }

        @Override
        public void didError(String message) { //case if we find an error in fetching
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT); //we show fail message in the case we fail to fetch

        } //the listener lets us know if we fetched or failed and based on that sets the recycler view or shows a fail message
    };
}