package com.example.factsaboutnumber;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;


public class MainActivity extends AppCompatActivity {
    EditText numberField;
    ProgressBar loadingPB;
    LinearLayout requestHistory;
    Retrofit retrofitJson;
    RetrofitAPI retrofitAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numberField = findViewById(R.id.editTextNumberDecimal);
        requestHistory = findViewById(R.id.requestHistory);
        loadingPB = findViewById(R.id.idLoadingPB);


        retrofitJson = new Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofitJson.create(RetrofitAPI.class);
    }


    private void getFact(String number) {
        Call<FactAboutNumber> call = retrofitAPI.getFact(number);
        call.enqueue(new Callback<FactAboutNumber>() {
            @Override
            public void onResponse(Call<FactAboutNumber> call, Response<FactAboutNumber> response) {
                loadingPB.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    FactAboutNumber modal = response.body();
                    TextView fact = new TextView(MainActivity.this);
                    fact.setTextColor(Color.WHITE);
                    fact.setText(modal.getText());
                    numberField.setText("");
                    requestHistory.addView(fact);
                }else {
                    TextView fact = new TextView(MainActivity.this);
                    fact.setTextColor(Color.WHITE);
                    fact.setText(response.code() + "");
                    requestHistory.addView(fact);
                }

            }

            @Override
            public void onFailure(Call<FactAboutNumber> call, Throwable t) {
                loadingPB.setVisibility(View.GONE);
                // displaying an error message in toast
                Toast.makeText(MainActivity.this, "Не удалось получить данные с сервера. Попробуйте снова", Toast.LENGTH_SHORT).show();
            }
        });
    }


    public void getFact(View view) {
        String inputNumber = numberField.getText().toString();

        if (inputNumber.isEmpty() || !TextUtils.isDigitsOnly(inputNumber)) {
            Toast.makeText(MainActivity.this, "Введите целое число", Toast.LENGTH_SHORT).show();
        } else {
            loadingPB.setVisibility(View.VISIBLE);
            getFact(inputNumber);
        }
    }


    public void getFactAboutRandomNumber(View view) {
        numberField.setText("");
        loadingPB.setVisibility(View.VISIBLE);
        getFact("random");
    }

    public void clearHistory(View view) {
        requestHistory.removeAllViews();
    }


    public interface RetrofitAPI {
        @GET("{number}?json")
        Call<FactAboutNumber> getFact(@Path("number") String number);
    }


    private static class FactAboutNumber {
        private String text;

        public FactAboutNumber(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }


}

