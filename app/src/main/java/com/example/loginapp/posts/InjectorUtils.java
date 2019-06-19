package com.example.loginapp.posts;

import com.example.loginapp.data.AppRepository;
import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.posts.add_post_activity.AddPostViewModelFactory;
import com.example.loginapp.posts.post_activity.PostsViewModelFactory;

public class InjectorUtils {


    public static NetworkDataSource getNetworkDataSource(){
        return NetworkDataSource.getInstance();
    }

    public static AppRepository getAppRepository(){
        return AppRepository.getInstance(getNetworkDataSource());
    }

    public static PostsViewModelFactory getPostsActivityViewModelFactory(){
        return new PostsViewModelFactory(getAppRepository());
    }

    public static AddPostViewModelFactory getAddPostsViewModelFactory(){
        return new AddPostViewModelFactory(getAppRepository());
    }
}
