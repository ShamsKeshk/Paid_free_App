package com.example.loginapp.data;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.loginapp.common.AppExecutors;
import com.example.loginapp.data.database.PostsDao;
import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.posts.post_activity.Post;

import java.util.List;
import java.util.PrimitiveIterator;

public class AppRepository {

    private static final String TAG = AppRepository.class.getSimpleName();

    private static final Object LOCK = new Object();

    private static AppRepository appRepository;

    private NetworkDataSource networkDataSource;
    private PostsDao mPostsDao;
    private AppExecutors mAppExecutors;
    private MutableLiveData<Integer> mIntegerMutableLiveData = new MutableLiveData<>();

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

    private void createPostToDatabase(Post post){
        createPost(post).observeForever(new Observer<Post>() {
            @Override
            public void onChanged(Post post) {
                mAppExecutors.getDiskIo().execute(new Runnable() {
                    @Override
                    public void run() {
                     int id =(int)   mPostsDao.createPost(post);
                        Log.e(TAG,"Inserted Post is : " + id);
                        mIntegerMutableLiveData.postValue(id);
                    }
                });
            }
        });
    }

    private MutableLiveData<Post> postMutableLiveData = new MutableLiveData<>();

    public void getCreatedPost(Post posst){
        createPostToDatabase(posst);
      mIntegerMutableLiveData.observeForever(new Observer<Integer>() {
          @Override
          public void onChanged(Integer integer) {
              Log.e(TAG," Get Created Post : " + integer);

              getFromDatabase(integer).observeForever(new Observer<Post>() {
                  @Override
                  public void onChanged(Post post) {
                      Log.e(TAG," Post Value Of MutableLive data : " + post.getTitle());

                      postMutableLiveData.postValue(post);
                  }
              });
          }
      });
    }

    public MutableLiveData<Post> getPostMutableLiveData(Post post) {
        getCreatedPost(post);
        return postMutableLiveData;
    }

    private LiveData<Post> getFromDatabase(Integer integer){
        LiveData<Post> liveData = mPostsDao.getPostById(integer);
        Log.e(TAG," Get Live Data Of Posts from database By Integer");
        return liveData;
    }


}
