package com.example.miniresearch.Listeners;

import com.example.miniresearch.Models.RandomRecipeAPIResponse;

public interface RandomRecipeResponseListener {                       //A response listerer for our api, lets us know if we fetched or got error
    void didFetch(RandomRecipeAPIResponse response, String message);
    void didError(String message);
}
