package com.larryngo.fetchrewardsexercise;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Retrofit retrofit;
    private FetchAPI service;

    List<FetchObject> responseList = new ArrayList<FetchObject>();
    private GridView gv;
    private GridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set up
        gv = findViewById(R.id.fetch_grid);
        adapter = new GridAdapter(getApplicationContext(), responseList);
        gv.setAdapter(adapter);

        //Using retrofit library to connect to the given URL
        retrofit = new Retrofit.Builder()
                .baseUrl(FetchAPI.baseURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //will provide the framework
        service = retrofit.create(FetchAPI.class);

        //collects the data and initializes it to a grid to show
        collectData();
    }

    public void collectData () {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Call<List<FetchObject>> call = service.getJson();
                call.enqueue(new Callback<List<FetchObject>>() {
                    @Override
                    public void onResponse(Call<List<FetchObject>> call, Response<List<FetchObject>> response) {
                        if(response.isSuccessful()) {
                            responseList = response.body(); //gets the entire array of objects

                            List<FetchObject> fetchList = new ArrayList<>(); //this will be the list of real objects, which filters out the objects without names

                            for(FetchObject obj : responseList) { //goes through all the objects in the json file
                                if(obj.getName() != null && !obj.getName().equals("")) { //checks if there are no names associated
                                    fetchList.add(obj); //adding object to the real list
                                }
                            }

                            //Sorted by listid, then name.
                            Collections.sort(fetchList, FetchObject.COMPARE_BY_NAME); //Sort by name first
                            Collections.sort(fetchList, FetchObject.COMPARE_BY_LISTID); //Sort by listid second (making the list finally sorted by listid, then name)
                            adapter.addDataList(fetchList); //shows up on the grid
                        }
                    }

                    @Override
                    public void onFailure(Call<List<FetchObject>> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Could not connect to the URL", Toast.LENGTH_SHORT).show();
                        System.out.println(t.getMessage()); //error message, (could not connect to URL)
                    }
                });
            }
        }).start();

    }
}