package com.example.loginapp.data.sync_network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.loginapp.posts.Posts;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncPosts {

    public static final String TAG = SyncPosts.class.getSimpleName();


    public static void sync (MutableLiveData<List<Posts>> listMutableLiveData){

        Call<List<Posts>> listCall = NetworkUtils.getInstance().getJsonPlaceHolderApi().getAllPosts();

        listCall.enqueue(new Callback<List<Posts>>() {
            @Override
            public void onResponse(Call<List<Posts>> call, Response<List<Posts>> response) {
                if (response.isSuccessful()){
                    listMutableLiveData.postValue(response.body());
                    Log.e(TAG,"POSTS RESULT : " + response.body());
                }else {
                    Log.e(TAG,"POSTS RESULT : Not Success " + response.body());

                }
            }

            @Override
            public void onFailure(Call<List<Posts>> call, Throwable t) {
                Log.e(TAG,"POSTS RESULT FAILED: " + t.getMessage());
            }
        });
    }

    public static void createPost(MutableLiveData<Posts> postsMutableLiveData,Posts posts){
        Call<Posts> postsCall = NetworkUtils.getInstance().getJsonPlaceHolderApi().createPost(posts);
        postsCall.enqueue(new Callback<Posts>() {
            @Override
            public void onResponse(Call<Posts> call, Response<Posts> response) {
                if (response.isSuccessful()){
                    postsMutableLiveData.postValue(response.body());
                    Log.e(TAG,"POSTS RESULT CREATE POST: " + response.body().getTitle());
                }else {
                    Log.e(TAG,"POSTS RESULT CREATE POST: Not Success " + response.body());

                }
            }

            @Override
            public void onFailure(Call<Posts> call, Throwable t) {

            }
        });
    }
}
