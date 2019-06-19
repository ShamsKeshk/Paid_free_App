package com.example.loginapp.data.sync_network;

import com.example.loginapp.posts.post_activity.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface JsonPlaceHolderApi {

    @GET("/posts")
    Call<List<Post>> getAllPosts();

    @POST("/posts")
    Call<Post> createPost(@Body Post post);
}
