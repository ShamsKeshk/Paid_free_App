package com.example.loginapp.common.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.loginapp.posts.post_activity.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncPosts {

    private static final String TAG = SyncPosts.class.getSimpleName();


    public static void sync(MutableLiveData<List<Post>> listMutableLiveData) {

        Call<List<Post>> listCall = NetworkUtils.getInstance().getJsonPlaceHolderApi().getAllPosts();

        listCall.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (response.isSuccessful()) {
                    listMutableLiveData.postValue(response.body());
                    Log.e(TAG, "POSTS RESULT : " + response.body());
                } else {
                    Log.e(TAG, "POSTS RESULT : Not Success " + response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                Log.e(TAG, "POSTS RESULT FAILED: " + t.getMessage());
            }
        });
    }

    public static void createPost(MutableLiveData<Post> postsMutableLiveData, Post posts) {
        Call<Post> postsCall = NetworkUtils.getInstance().getJsonPlaceHolderApi().createPost(posts);
        postsCall.enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {
                if (response.isSuccessful()) {
                    postsMutableLiveData.postValue(response.body());
                    Log.e(TAG, "POSTS RESULT CREATE POST: " + response.body().getTitle());
                } else {
                    Log.e(TAG, "POSTS RESULT CREATE POST: Not Success " + response.body());

                }
            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }
}
