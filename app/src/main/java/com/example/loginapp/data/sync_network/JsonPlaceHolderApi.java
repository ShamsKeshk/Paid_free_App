package com.example.loginapp.data.sync_network;

import com.example.loginapp.posts.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("/posts")
    Call<List<Posts>> getAllPosts();

    @POST("/posts")
    Call<Posts> createPost(@Body Posts post);
}
