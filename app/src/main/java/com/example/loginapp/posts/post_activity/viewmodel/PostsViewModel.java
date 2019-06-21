package com.example.loginapp.posts.post_activity.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.posts.PostsRepository;
import com.example.loginapp.posts.post_activity.model.Post;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private final PostsRepository mPostsRepository;

    private final LiveData<List<Post>> listLiveData;

    public PostsViewModel(PostsRepository postsRepository) {
        this.mPostsRepository = postsRepository;
        listLiveData = postsRepository.getAllPosts();
    }

    public LiveData<List<Post>> getListLiveData() {
        return listLiveData;
    }
}
