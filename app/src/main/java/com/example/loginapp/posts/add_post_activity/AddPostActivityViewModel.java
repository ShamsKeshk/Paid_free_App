package com.example.loginapp.posts.add_post_activity;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.data.AppRepository;
import com.example.loginapp.posts.post_activity.Post;

public class AddPostActivityViewModel extends ViewModel {


    public MutableLiveData<String> mPostTitle = new MutableLiveData<>();
    public MutableLiveData<String> mPostDesc = new MutableLiveData<>();
    public MutableLiveData<String> mPostUserId = new MutableLiveData<>();

    private MutableLiveData<Post> postsMutableLiveData;

    private AppRepository appRepository;

    public AddPostActivityViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;
        postsMutableLiveData = new MutableLiveData<>();
    }

    public LiveData<Post> getPostsLiveData(Post post) {
       // return appRepository.createPost(post);
        return appRepository.createPost(post);
    }

    public MutableLiveData<Post> getPostsMutableLiveData() {
        if (postsMutableLiveData == null) {
            postsMutableLiveData = new MutableLiveData<>();
        }
        return postsMutableLiveData;
    }

    public void onClick(View view) {
        Post posts = getNewPostValues();
        postsMutableLiveData.setValue(posts);
    }

    private Post getNewPostValues() {
        String title = mPostTitle.getValue();
        String desc = mPostDesc.getValue();
        int userId;
        if (mPostUserId.getValue() == null) {
            userId = -1;
        } else {
            userId = Integer.parseInt(mPostUserId.getValue());
        }

        return new Post(userId, title, desc);
    }
}
