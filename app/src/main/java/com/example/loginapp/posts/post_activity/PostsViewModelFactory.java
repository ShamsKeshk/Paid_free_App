package com.example.loginapp.posts.post_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.data.AppRepository;
import com.example.loginapp.posts.post_activity.PostsViewModel;

public class PostsViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private AppRepository appRepository;

    public PostsViewModelFactory(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new PostsViewModel(appRepository);
    }
}
