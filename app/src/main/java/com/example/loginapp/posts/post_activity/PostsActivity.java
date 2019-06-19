package com.example.loginapp.posts.post_activity;

import android.content.Intent;
import android.os.Bundle;

import com.example.loginapp.posts.InjectorUtils;
import com.example.loginapp.posts.add_post_activity.AddPostActivity;
import com.example.loginapp.R;
import com.example.loginapp.posts.Posts;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsActivity extends AppCompatActivity implements PostAdapter.OnPostsListItemClicked {

    @BindView(R.id.rv_posts_list_id)
    RecyclerView mRecyclerView;
    @BindView(R.id.progress_bar_post_activity_id)
    ProgressBar mProgressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private List<Posts> mPosts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posts);
        ButterKnife.bind(this);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Create Post", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                openAddPostActivity();
            }
        });

        PostAdapter postAdapter = new PostAdapter(null,this::onPostClicked);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(postAdapter);

        PostsViewModelFactory postsViewModelFactory =
                InjectorUtils.getPostsActivityViewModelFactory();

        PostsViewModel postsViewModel = ViewModelProviders.of(this,postsViewModelFactory).get(PostsViewModel.class);

        postsViewModel.getListLiveData().observe(this, new Observer<List<Posts>>() {
            @Override
            public void onChanged(List<Posts> posts) {
                mProgressBar.setVisibility(View.GONE);
                postAdapter.setPostsList(posts);
                mPosts = posts;

            }
        });
    }

    private void openAddPostActivity(){
        Intent intent = new Intent(PostsActivity.this, AddPostActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPostClicked(int position) {
        Toast.makeText(getApplicationContext(),mPosts.get(position).getTitle(),Toast.LENGTH_LONG).show();
    }
}
