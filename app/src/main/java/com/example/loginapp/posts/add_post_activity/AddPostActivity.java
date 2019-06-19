package com.example.loginapp.posts.add_post_activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.data.AppRepository;
import com.example.loginapp.data.sync_network.NetworkDataSource;
import com.example.loginapp.databinding.ActivityAddPostBinding;
import com.example.loginapp.posts.Posts;

public class AddPostActivity extends AppCompatActivity {

 ActivityAddPostBinding binding;

 TextView textView ;

    AddPostActivityViewModel addPostActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        NetworkDataSource networkDataSource = NetworkDataSource.getInstance();
        AppRepository appRepository = AppRepository.getInstance(networkDataSource);

        AddPostViewModelFactory addPostViewModelFactory = new AddPostViewModelFactory(appRepository);
        addPostActivityViewModel = ViewModelProviders.of(this,addPostViewModelFactory)
                .get(AddPostActivityViewModel.class);


        binding = DataBindingUtil.setContentView(AddPostActivity.this,R.layout.activity_add_post);


        binding.setAddPostActivityViewModel(addPostActivityViewModel);
        binding.setLifecycleOwner(this);



        addPostActivityViewModel.getPostsMutableLiveData().observe(this, new Observer<Posts>() {
            @Override
            public void onChanged(Posts posts) {
                Toast.makeText(getApplicationContext(),"Post changed ",Toast.LENGTH_LONG).show();
              createPost(posts);
            }
        });


    }
    private void createPost(Posts posts){
        Toast.makeText(getApplicationContext(),"Post changed from create Post ",Toast.LENGTH_LONG).show();

        addPostActivityViewModel.getPostsLiveData(posts).observe(this, new Observer<Posts>() {
            @Override
            public void onChanged(Posts posts) {
                Toast.makeText(getApplicationContext(),"Post value " + posts.getTitle(),Toast.LENGTH_LONG).show();
                String postTitle = posts.getTitle();
                String postDesc = posts.getBody();
                int postUserId = posts.getUserId();
                int postId = posts.getId();
                binding.tvCreatedPostResultId.setText(postTitle + "\n" +postDesc+"\n"+postUserId+"\n"+postId);
            }
        });


    }
}
