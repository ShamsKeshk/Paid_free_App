package com.example.loginapp.posts.add_post_activity;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.data.AppRepository;
import com.example.loginapp.posts.Posts;

public class AddPostActivityViewModel extends ViewModel {


    public MutableLiveData<String> mPostTitle = new MutableLiveData<>();
    public MutableLiveData<String> mPostDesc = new MutableLiveData<>();
    public MutableLiveData<String> mPostUserId = new MutableLiveData<>();

    private MutableLiveData<Posts> postsMutableLiveData ;

    private AppRepository appRepository;

    public AddPostActivityViewModel(AppRepository appRepository){
        this.appRepository = appRepository;
        postsMutableLiveData =new MutableLiveData<>();
    }

    public LiveData<Posts> getPostsLiveData(Posts post) {
          return appRepository.createPost(post);
    }

    public MutableLiveData<Posts> getPostsMutableLiveData(){
        if (postsMutableLiveData == null){
            postsMutableLiveData = new MutableLiveData<>();
        }
        return postsMutableLiveData;
    }

    public void onClick(View view){
        String title = mPostTitle.getValue();
        String desc = mPostDesc.getValue();
        int userId = Integer.parseInt(mPostUserId.getValue());
        Posts posts = new Posts(userId,title,desc);
        postsMutableLiveData.setValue(posts);
    }

}
