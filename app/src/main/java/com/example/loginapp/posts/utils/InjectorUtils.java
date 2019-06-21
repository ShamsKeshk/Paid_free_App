package com.example.loginapp.posts.utils;

import android.content.Context;

import com.example.loginapp.common.AppExecutors;
import com.example.loginapp.common.database.PostsDao;
import com.example.loginapp.common.database.PostsDatabase;
import com.example.loginapp.common.network.NetworkDataSource;
import com.example.loginapp.posts.PostsRepository;
import com.example.loginapp.posts.add_post_activity.viewmodel.AddPostViewModelFactory;
import com.example.loginapp.posts.post_activity.viewmodel.PostsViewModelFactory;

public final class InjectorUtils {

    private InjectorUtils(){}

    public static NetworkDataSource getNetworkDataSource() {
        return NetworkDataSource.getInstance();
    }

    public static PostsRepository getAppRepository(Context context) {
        PostsDao postsDao = getPostsDatabase(context).postsDao();
        return PostsRepository.getInstance(getNetworkDataSource(),postsDao,getAppExecutors());
    }

    public static AppExecutors getAppExecutors(){
        return AppExecutors.getInstance();
    }

    public static PostsDatabase getPostsDatabase(Context context){
        return PostsDatabase.getInstance(context);
    }

    public static PostsViewModelFactory getPostsActivityViewModelFactory(Context context) {
        return new PostsViewModelFactory(getAppRepository(context));
    }

    public static AddPostViewModelFactory getAddPostsViewModelFactory(Context context) {
        return new AddPostViewModelFactory(getAppRepository(context));
    }
}
