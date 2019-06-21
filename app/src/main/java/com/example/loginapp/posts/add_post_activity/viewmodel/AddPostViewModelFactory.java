package com.example.loginapp.posts.add_post_activity.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.loginapp.posts.PostsRepository;

public class AddPostViewModelFactory extends ViewModelProvider.NewInstanceFactory {

    private PostsRepository mPostsRepository;

    public AddPostViewModelFactory(PostsRepository postsRepository) {
        this.mPostsRepository = postsRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new AddPostActivityViewModel(mPostsRepository);
    }
}
