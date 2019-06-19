package com.example.loginapp.posts.post_activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.data.AppRepository;
import com.example.loginapp.posts.Posts;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private final AppRepository appRepository;

    private final LiveData<List<Posts>> listLiveData;


    public PostsViewModel(AppRepository appRepository){
        this.appRepository = appRepository;

        listLiveData = appRepository.getPosts();
    }

    public LiveData<List<Posts>> getListLiveData() {
        return listLiveData;
    }
}
