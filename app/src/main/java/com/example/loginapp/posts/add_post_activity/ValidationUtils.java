package com.example.loginapp.posts.add_post_activity;

import android.text.TextUtils;

import com.example.loginapp.databinding.ActivityAddPostBinding;
import com.example.loginapp.posts.post_activity.model.Post;

import java.util.Objects;

public final class ValidationUtils {

    private ValidationUtils() {
    }

    public static boolean isDataValidToCreate(Post post, ActivityAddPostBinding binding) {
        if (post.getTitle() == null || TextUtils.isEmpty(Objects.requireNonNull(post).getTitle().trim())) {
            binding.etPostTitleAddActivityId.setError("Enter a valid Title");
            binding.etPostTitleAddActivityId.requestFocus();
            return false;
        } else if (!isTitleValid(post)) {
            binding.etPostTitleAddActivityId.setError("Enter a valid Title");
            binding.etPostTitleAddActivityId.requestFocus();
            return false;
        } else if (post.getBody() == null || TextUtils.isEmpty(Objects.requireNonNull(post).getBody())) {
            binding.etPostDescriptionAddActivityId.setError("Enter a Description");
            binding.etPostDescriptionAddActivityId.requestFocus();
            return false;
        } else if (!isPostDescriptionValid(post)) {
            binding.etPostDescriptionAddActivityId.setError("Enter at least 10 Digit password");
            binding.etPostDescriptionAddActivityId.requestFocus();
            return false;
        } else if (!isPostUserIdValid(post)) {
            binding.etPostUserIdAddActivityId.setError("Enter a User Id");
            binding.etPostUserIdAddActivityId.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private static boolean isTitleValid(Post post) {
        return post.getTitle().length() > 2;
    }

    private static boolean isPostDescriptionValid(Post post) {
        return post.getBody().length() > 10;
    }

    private static boolean isPostUserIdValid(Post post) {
        return post.getUserId() > 0;
    }
}
