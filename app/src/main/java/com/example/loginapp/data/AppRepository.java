package com.example.loginapp.data;

import androidx.lifecycle.LiveData;

import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.posts.Posts;

import java.util.List;

public class AppRepository {

    private static final Object LOCK = new Object();

    private static AppRepository appRepository;

    private NetworkDataSource networkDataSource;

    private AppRepository(NetworkDataSource networkDataSource){
        this.networkDataSource = networkDataSource;
    }

    public static AppRepository getInstance(NetworkDataSource networkDataSource){
        if (appRepository == null){
            synchronized (LOCK){
                appRepository = new AppRepository(networkDataSource);
            }
        }
        return appRepository;
    }

    public LiveData<List<Posts>> getPosts(){
        networkDataSource.fetchPosts();
        return networkDataSource.getPostsList();
    }

    public LiveData<Posts> createPost(Posts posts){
        networkDataSource.createPost(posts);
        return networkDataSource.getPostsMutableLiveData();
    }


}
