package com.example.mornhousetesttask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {
    static String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getFact(10);
    }



    private void getFact(int number) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<FactAboutNumber> call = retrofitAPI.getFact(number);
        call.enqueue(new Callback<FactAboutNumber>() {
            @Override
            public void onResponse(Call<FactAboutNumber> call, Response<FactAboutNumber> response) {
                if (response.isSuccessful()) {
                    FactAboutNumber modal = response.body();
                    TextView fact = (TextView) findViewById(R.id.text);
                    fact.setTextColor(Color.WHITE);
                    fact.setText(modal.getText());
                }
            }

            @Override
            public void onFailure(Call<FactAboutNumber> call, Throwable t) {
                // displaying an error message in toast
                Toast.makeText(MainActivity.this, "Fail to get the data..", Toast.LENGTH_SHORT).show();
            }
        });
    }
//    public String getFact(int number){
//
//        return "";
//    }

//    private String getFactAboutRandomNumber (){
//
//        return "";
//    }



    public interface RetrofitAPI {
        @GET("{number}?json")
        Call<FactAboutNumber> getFact(@Path("number") int number);
    }




}

