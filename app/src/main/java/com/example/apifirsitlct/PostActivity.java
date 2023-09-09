package com.example.apifirsitlct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.apifirsitlct.databinding.ActivityPostBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {
    ActivityPostBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        int receptiveId = intent.getIntExtra("id",-1);
        API_Queries api =  MyRetrofit.getInstance().create(API_Queries.class);
        api.getPostById(receptiveId).enqueue(new Callback<List<Posts>>() {
        @Override
        public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
            if(response.isSuccessful()){
                List<Posts> post  = response.body();
                fillINAdapter(post);
            }else{
                Toast.makeText(PostActivity.this, "Request Failed Successfully", Toast.LENGTH_LONG).show();
            }
        }
        @Override
        public void onFailure(Call<List<Posts>> call, Throwable t) {
            Toast.makeText(PostActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();


        }
            });
    }


    private void fillINAdapter(List<Posts> postsList) {
        PostsAdapter adapter = new PostsAdapter(postsList ,this);
        binding.rvPost.setAdapter(adapter);
        binding.rvPost.setHasFixedSize(true);
        binding.rvPost.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCommentClick(new CommentClick() {
            @Override
            public void ComClick(int commentId) {
                Intent intent = new Intent(getBaseContext(),CommentsActivity.class);
                intent.putExtra("Id",commentId);
                startActivity(intent);

            }
        });

    }
}