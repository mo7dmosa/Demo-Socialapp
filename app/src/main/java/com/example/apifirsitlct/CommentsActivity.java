package com.example.apifirsitlct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.apifirsitlct.databinding.ActivityCommentsBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommentsActivity extends AppCompatActivity {
    ActivityCommentsBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCommentsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int receptiveId = intent.getIntExtra("Id",-1);
        Log.d("jma",receptiveId+"");
        API_Queries api =  MyRetrofit.getInstance().create(API_Queries.class);
        api.getCommentsById(receptiveId).enqueue(new Callback<List<Comments>>() {
            @Override
            public void onResponse(Call<List<Comments>> call, Response<List<Comments>> response) {
                if(response.isSuccessful()){
                    List<Comments> comments  = response.body();
                    fillDataInComRV(comments);
                }else{
                    Toast.makeText(CommentsActivity.this, "Request Failed Successfully", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<Comments>> call, Throwable t) {
                Toast.makeText(CommentsActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

    private void fillDataInComRV(List<Comments> commentsList) {
        CommentsAdapter adapter = new CommentsAdapter(commentsList ,this);
        binding.rvComments.setAdapter(adapter);
        binding.rvComments.setHasFixedSize(true);
        binding.rvComments.setLayoutManager(new LinearLayoutManager(this));
}}