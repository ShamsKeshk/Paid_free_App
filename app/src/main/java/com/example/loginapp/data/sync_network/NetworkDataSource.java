package com.example.loginapp.data.sync_network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginapp.posts.post_activity.Post;

import java.util.List;

public class NetworkDataSource {

    private static final Object LOCK = new Object();
    private static NetworkDataSource sNetworkDataSource;
    private MutableLiveData<List<Post>> listMutableLiveData;
    private MutableLiveData<Post> postsMutableLiveData;

    private NetworkDataSource() {
        listMutableLiveData = new MutableLiveData<>();
        postsMutableLiveData = new MutableLiveData<>();
    }

    public static NetworkDataSource getInstance() {
        if (sNetworkDataSource == null) {
            synchronized (LOCK) {
                sNetworkDataSource = new NetworkDataSource();
            }
        }
        return sNetworkDataSource;
    }

    public LiveData<List<Post>> getPostsList() {
        return listMutableLiveData;
    }

    public LiveData<Post> getPostsMutableLiveData() {
        return postsMutableLiveData;
    }

    public void fetchPosts() {
        SyncPosts.sync(listMutableLiveData);
    }

    public void createPost(Post post) {
        SyncPosts.createPost(postsMutableLiveData, post);
    }
}
