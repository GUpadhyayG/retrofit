package com.example.retrofit;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {


    interface RequestUser {
        @GET("/api/users/{uid}")
        Call<UserData>getUser(@Path("uid") String uid);


    }

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        RequestUser requestUser = retrofit.create(RequestUser.class);
        requestUser.getUser("3").enqueue(new Callback<>() {
            @Override
            public void onResponse(Call call, Response response) {
                textView.setText(response.body().first_name);

            }

            @Override
            public void onFailure(Call call, Throwable throwable) {
                textView.setText(throwable.getMessage());

            }
        });


    }


}
