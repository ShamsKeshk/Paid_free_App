package com.example.loginapp.data.sync_network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginapp.posts.Posts;

import java.util.List;

public class NetworkDataSource {

    private static NetworkDataSource sNetworkDataSource;

    private static final Object LOCK = new Object();

    private MutableLiveData<List<Posts>> listMutableLiveData;
    private MutableLiveData<Posts> postsMutableLiveData;

    private NetworkDataSource(){
        listMutableLiveData = new MutableLiveData<>();
        postsMutableLiveData = new MutableLiveData<>();
    }

    public static NetworkDataSource getInstance(){
        if (sNetworkDataSource == null){
            synchronized (LOCK){
                sNetworkDataSource = new NetworkDataSource();
            }
        }
        return sNetworkDataSource;
    }

    public LiveData<List<Posts>> getPostsList(){
        return listMutableLiveData;
    }

    public LiveData<Posts> getPostsMutableLiveData() {
        return postsMutableLiveData;
    }

    public void fetchPosts(){
        SyncPosts.sync(listMutableLiveData);
    }

    public void createPost(Posts post){
        SyncPosts.createPost(postsMutableLiveData,post);
    }
}
