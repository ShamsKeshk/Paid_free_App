package com.example.loginapp.posts.add_post_activity.view;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityAddPostBinding;
import com.example.loginapp.posts.utils.ExtraConstants;
import com.example.loginapp.posts.utils.InjectorUtils;
import com.example.loginapp.posts.add_post_activity.viewmodel.AddPostActivityViewModel;
import com.example.loginapp.posts.add_post_activity.viewmodel.AddPostViewModelFactory;
import com.example.loginapp.posts.add_post_activity.ValidationUtils;
import com.example.loginapp.posts.post_activity.model.Post;

public class AddPostActivity extends AppCompatActivity {

    private ActivityAddPostBinding binding;

    private AddPostActivityViewModel addPostActivityViewModel;

    private Post mPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViewModel();
        initDataBinding(addPostActivityViewModel);

        if (getIntent() != null) {
            if (getIntent().hasExtra(ExtraConstants.EXTRA_POST_CONTENT)) {
                mPost = getIntent().getParcelableExtra(ExtraConstants.EXTRA_POST_CONTENT);
                Toast.makeText(getApplicationContext(), " post value : " + mPost.getTitle(), Toast.LENGTH_LONG).show();
                setPostValuesToViews(mPost);
            }
        }

        addPostActivityViewModel.getPostsMutableLiveData().observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post posts) {
                if (ValidationUtils.isDataValidToCreate(posts, binding)) {
                        Toast.makeText(getApplicationContext(), "Post changed ", Toast.LENGTH_LONG).show();
                        createPost(posts);
                    }
            }
        });

    }

    private void initViewModel() {
        AddPostViewModelFactory addPostViewModelFactory = InjectorUtils.getAddPostsViewModelFactory(this);
        addPostActivityViewModel = ViewModelProviders.of(this, addPostViewModelFactory)
                .get(AddPostActivityViewModel.class);
    }

    private void initDataBinding(AddPostActivityViewModel addPostActivityViewModel) {
        binding = DataBindingUtil.setContentView(AddPostActivity.this, R.layout.activity_add_post);
        binding.setAddPostActivityViewModel(addPostActivityViewModel);
        binding.setLifecycleOwner(this);
    }

    private void createPost(Post posts) {
        Toast.makeText(getApplicationContext(), "Post changed from create Post ", Toast.LENGTH_LONG).show();

        addPostActivityViewModel.getPostsLiveData(posts).observe(this, new Observer<Post>() {
            @Override
            public void onChanged(Post posts) {
                Toast.makeText(getApplicationContext(), "Post value " + posts.getTitle(), Toast.LENGTH_LONG).show();
                String postTitle = posts.getTitle();
                String postDesc = posts.getBody();
                int postUserId = posts.getUserId();
                int postId = posts.getId();
                binding.tvCreatedPostResultId.setText(postTitle + "\n" + postDesc + "\n" + postUserId + "\n" + postId);
            }
        });
    }

    private void setPostValuesToViews(Post post) {
        addPostActivityViewModel.mPostTitle.setValue(post.getTitle());
        addPostActivityViewModel.mPostDesc.setValue(post.getBody());
        addPostActivityViewModel.mPostUserId.setValue(String.valueOf(post.getUserId()));
    }
}
