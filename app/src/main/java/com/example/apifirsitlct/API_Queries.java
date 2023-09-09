package com.example.apifirsitlct;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API_Queries {
    @GET("/posts")
    Call<List<Posts>>getPostById(@Query("userId") int userId);

    @GET("/users")
    Call<List<User>> getAllUsers();

    @GET("/comments")
    Call<List<Comments>> getCommentsById(@Query("postId") int postId);

}
