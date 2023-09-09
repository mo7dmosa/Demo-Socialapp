package com.example.apifirsitlct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.widget.Toast;

import com.example.apifirsitlct.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        API_Queries api =  MyRetrofit.getInstance().create(API_Queries.class);
        api.getAllUsers().enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()){
                    List<User> usersList = response.body();
                    Log.d("msg",usersList.size()+"");
                    fillDataInRV(usersList);

                     Toast.makeText(MainActivity.this, "list size: "+usersList.size(), Toast.LENGTH_LONG).show();

                }else{
                                        Toast.makeText(MainActivity.this, "Request Failed Successfully", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

//        api.getAllPosts().enqueue(new Callback<List<Posts>>() {
//            @Override
//            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
//                if (response.isSuccessful()){
//                    List<Posts> postsList = response.body();
//                    Log.d("t",postsList.size()+"");
//                    fillDataInRV(postsList);
//
//                    Toast.makeText(MainActivity.this, postsList.size()+"", Toast.LENGTH_LONG).show();
//                }else {
//                    Toast.makeText(MainActivity.this, "Request Failed Successfully", Toast.LENGTH_LONG).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<Posts>> call, Throwable t) {
//                Toast.makeText(MainActivity.this, "Failed: " + t.getMessage(), Toast.LENGTH_LONG).show();
//
//            }
//        });
    }

    private void fillDataInRV(List<User> usersList) {
        UserAdapter adapter = new UserAdapter(usersList ,this);
        binding.rv.setAdapter(adapter);
        binding.rv.setHasFixedSize(true);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        adapter.setClick(new Clicks() {
            @Override
            public void onClicks(int userId) {
                Intent intent = new Intent(getBaseContext(),PostActivity.class);
                intent.putExtra("id",userId);
                startActivity(intent);
            }
        });


    }
}