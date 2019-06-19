package com.example.loginapp.posts.add_post_activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.data.AppRepository;

public class AddPostViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private AppRepository appRepository;

    public AddPostViewModelFactory(AppRepository appRepository) {
        this.appRepository = appRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddPostActivityViewModel(appRepository);
    }
}
