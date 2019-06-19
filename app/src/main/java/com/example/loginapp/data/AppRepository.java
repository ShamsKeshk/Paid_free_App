package com.example.loginapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.loginapp.common.AppExecutors;
import com.example.loginapp.data.database.PostsDao;
import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.posts.post_activity.Post;

import java.util.List;

public class AppRepository {

    private static final Object LOCK = new Object();

    private static AppRepository appRepository;

    private NetworkDataSource networkDataSource;
    private PostsDao mPostsDao;
    private AppExecutors mAppExecutors;

    private AppRepository(NetworkDataSource networkDataSource,PostsDao postsDao ,AppExecutors appExecutors) {
        this.networkDataSource = networkDataSource;
        this.mPostsDao = postsDao;
        this.mAppExecutors = appExecutors;

        LiveData<List<Post>> listLiveData = getPosts();

       listLiveData.observeForever(new Observer<List<Post>>() {
           @Override
           public void onChanged(List<Post> posts) {
               appExecutors.getDiskIo().execute(new Runnable() {
                   @Override
                   public void run() {
                       mPostsDao.bulkInsert(posts);
                   }
               });
           }
       });
    }

    public static AppRepository getInstance(NetworkDataSource networkDataSource,PostsDao postsDao,AppExecutors appExecutors) {
        if (appRepository == null) {
            synchronized (LOCK) {
                appRepository = new AppRepository(networkDataSource,postsDao,appExecutors);
            }
        }
        return appRepository;
    }

    public LiveData<List<Post>> getPosts() {
        networkDataSource.fetchPosts();
        return networkDataSource.getPostsList();
    }

    public LiveData<List<Post>> getAllPosts(){
        return mPostsDao.getPosts();
    }

    public LiveData<Post> createPost(Post posts) {
        networkDataSource.createPost(posts);
        return networkDataSource.getPostsMutableLiveData();
    }


}
