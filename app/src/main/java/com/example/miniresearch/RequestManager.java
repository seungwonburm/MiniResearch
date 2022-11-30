package com.example.miniresearch;

import android.content.Context;

import com.example.miniresearch.Listeners.RandomRecipeResponseListener;
import com.example.miniresearch.Models.RandomRecipeAPIResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class RequestManager { //This is our Request manager to request GET information from the API
    Context context;
    Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build(); //We are using Retrofit because it assists us in turning HTTP API into a java interface for us. The code is based on their documentation

    public RequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipes(RandomRecipeResponseListener listener) {
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class); //Using retrofit we create java interface based on our api response we get
        Call<RandomRecipeAPIResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.api_key), "10"); //The number 10 shows how many results we wanna see and we are passing in our api key too. This is a call to the api for info
        call.enqueue(new Callback<RandomRecipeAPIResponse>() {  //we enqueue our call with API response listener to know if we got back a response from it. this is different then fetch because this is directed towards api information
            @Override
            public void onResponse(Call<RandomRecipeAPIResponse> call, Response<RandomRecipeAPIResponse> response) { //on response
                if (!response.isSuccessful()) { //if response was not successful
                    listener.didError(response.message()); //we show error message
                    return;
                }
                listener.didFetch(response.body(), response.message()); //or else we fetch the information from listerner.
            }

            @Override
            public void onFailure(Call<RandomRecipeAPIResponse> call, Throwable t) {
                listener.didError(t.getMessage()); //on failure we show error message
            }
        });
    }

    private interface CallRandomRecipes{ //interface to call random recipes
        @GET("recipes/random") //submitting a GET request for random recipe. An extension of base URL
        Call<RandomRecipeAPIResponse> callRandomRecipe( //Our request according to documentation requerires api key and a number which represents how many result we wanna see.
                @Query("apiKey") String apiKey, //query called apiKey and we obtain it from our apiKey
                @Query("number") String number //number of results we wanna see. I.e how many random recipes
        );
    }


}
