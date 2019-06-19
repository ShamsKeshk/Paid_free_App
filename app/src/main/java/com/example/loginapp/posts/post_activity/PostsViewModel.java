package com.example.loginapp.posts.post_activity;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.loginapp.data.AppRepository;

import java.util.List;

public class PostsViewModel extends ViewModel {

    private final AppRepository appRepository;

    private final LiveData<List<Post>> listLiveData;


    public PostsViewModel(AppRepository appRepository) {
        this.appRepository = appRepository;

        listLiveData = appRepository.getAllPosts();
    }

    public LiveData<List<Post>> getListLiveData() {
        return listLiveData;
    }
}
