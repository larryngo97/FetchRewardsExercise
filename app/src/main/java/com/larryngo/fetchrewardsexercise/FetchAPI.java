package com.larryngo.fetchrewardsexercise;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FetchAPI {
    String baseURL = "https://fetch-hiring.s3.amazonaws.com/";

    @GET("hiring.json")
    Call<List<FetchObject>> getJson();
}
