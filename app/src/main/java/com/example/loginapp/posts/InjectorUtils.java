package com.example.loginapp.posts;

import android.content.Context;

import com.example.loginapp.common.AppExecutors;
import com.example.loginapp.data.AppRepository;
import com.example.loginapp.data.database.PostsDao;
import com.example.loginapp.data.database.PostsDatabase;
import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.posts.add_post_activity.AddPostViewModelFactory;
import com.example.loginapp.posts.post_activity.PostsViewModelFactory;

public class InjectorUtils {


    public static NetworkDataSource getNetworkDataSource() {
        return NetworkDataSource.getInstance();
    }

    public static AppRepository getAppRepository(Context context) {
        PostsDao postsDao = getPostsDatabase(context).postsDao();
        return AppRepository.getInstance(getNetworkDataSource(),postsDao,getAppExecutors());
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
