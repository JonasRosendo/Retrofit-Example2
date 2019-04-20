package com.jonasrosendo.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jonasrosendo.retrofit.api.IDataService;
import com.jonasrosendo.retrofit.model.Photo;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Button btn_retrieve;
    private TextView txv_result;
    private Retrofit retrofit;
    private List<Photo> photoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_retrieve = findViewById(R.id.btn_retrieve);
        txv_result   = findViewById(R.id.txv_result);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        btn_retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retrieveListRetrofit();
            }
        });
    }

    private void retrieveListRetrofit(){

        IDataService dataService = retrofit.create(IDataService.class);
        Call<List<Photo>> callPhoto = dataService.retrievePhotos();

        callPhoto.enqueue(new Callback<List<Photo>>() {
            @Override
            public void onResponse(Call<List<Photo>> call, Response<List<Photo>> response) {
                if(response.isSuccessful()){
                    photoList = response.body();

                    for (int i = 0; i < photoList.size(); i++){
                        Photo photo = photoList.get(i);
                        Log.d("result", "Result: " + photo.getId() + "/" + photo.getTitle());
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Photo>> call, Throwable t) {

            }
        });
    }
}
