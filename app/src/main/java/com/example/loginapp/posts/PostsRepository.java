package com.example.loginapp.posts;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.loginapp.common.AppExecutors;
import com.example.loginapp.common.database.PostsDao;
import com.example.loginapp.common.network.NetworkDataSource;
import com.example.loginapp.posts.post_activity.model.Post;

import java.util.List;

public class PostsRepository {

    private static final String TAG = PostsRepository.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static PostsRepository sPostsRepository;

    private NetworkDataSource networkDataSource;
    private PostsDao mPostsDao;
    private AppExecutors mAppExecutors;
    private MutableLiveData<Integer> mIntegerMutableLiveData = new MutableLiveData<>();
    /*Add Post to Server ,
     receive the created Post and Insert it To Database
     and return the result to the User
     */
    private MutableLiveData<Post> postMutableLiveData = new MutableLiveData<>();

    private PostsRepository(NetworkDataSource networkDataSource, PostsDao postsDao, AppExecutors appExecutors) {
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

    public static PostsRepository getInstance(NetworkDataSource networkDataSource, PostsDao postsDao, AppExecutors appExecutors) {
        if (sPostsRepository == null) {
            synchronized (LOCK) {
                sPostsRepository = new PostsRepository(networkDataSource, postsDao, appExecutors);
            }
        }
        return sPostsRepository;
    }

    public LiveData<List<Post>> getPosts() {
        networkDataSource.fetchPosts();
        return networkDataSource.getPostsList();
    }

    public LiveData<List<Post>> getAllPosts() {
        return mPostsDao.getPosts();
    }

    public MutableLiveData<Post> getPostMutableLiveData(Post post) {
        getCreatedPost(post);
        return postMutableLiveData;
    }

    private LiveData<Post> createPostAtServer(Post posts) {
        networkDataSource.createPost(posts);
        return networkDataSource.getPostsMutableLiveData();
    }

    private void insertCreatedPostToDatabase(Post post) {
        createPostAtServer(post).observeForever(new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                mAppExecutors.getDiskIo().execute(new Runnable() {
                    @Override
                    public void run() {
                        int id = (int) mPostsDao.createPost(post);
                        Log.e(TAG, "Inserted Post is : " + id);
                        mIntegerMutableLiveData.postValue(id);
                    }
                });
            }
        });
    }

    private LiveData<Post> getCreatedPostFromDatabaseByRow(Integer integer) {
        LiveData<Post> liveData = mPostsDao.getPostById(integer);
        Log.e(TAG, " Get Live Data Of Posts from database By Integer");
        return liveData;
    }

    private void getCreatedPost(Post post) {
        insertCreatedPostToDatabase(post);
        mIntegerMutableLiveData.observeForever(new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                Log.e(TAG, " Get Created Post : " + integer);

                getCreatedPostFromDatabaseByRow(integer).observeForever(new Observer<Post>() {
                    @Override
                    public void onChanged(Post post) {
                        Log.e(TAG, " Post Value Of MutableLive data : " + post.getTitle());

                        postMutableLiveData.postValue(post);
                    }
                });
            }
        });
    }
}
