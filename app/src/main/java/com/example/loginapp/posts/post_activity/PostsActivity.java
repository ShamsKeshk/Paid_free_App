package com.example.loginapp.posts.post_activity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.common.NetworkState;
import com.example.loginapp.posts.ExtraConstants;
import com.example.loginapp.posts.InjectorUtils;
import com.example.loginapp.posts.add_post_activity.AddPostActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

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
    @BindView(R.id.crd_posts_activity_layout_id)
    CoordinatorLayout mCoordinatorLayout;

    private List<Post> mPosts;

    private void openNetworkIfNotOpened(){
        View parentLayout = findViewById(android.R.id.content);

        //FIXME this should run at onResume
        if(!NetworkState.isConnectedToNetwork(this)){
            Snackbar.make(mCoordinatorLayout, "No Internet Connection", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Connect Now", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(Settings.ACTION_WIFI_SETTINGS);
                            startActivity(intent);
                        }
                    }).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        openNetworkIfNotOpened();
    }

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
                openAddPostActivity();
            }
        });

        PostAdapter postAdapter = new PostAdapter(null, this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(postAdapter);

        PostsViewModelFactory postsViewModelFactory =
                InjectorUtils.getPostsActivityViewModelFactory(this);

        PostsViewModel postsViewModel = ViewModelProviders.of(this, postsViewModelFactory).get(PostsViewModel.class);

        postsViewModel.getListLiveData().observe(this, new Observer<List<Post>>() {
            @Override
            public void onChanged(List<Post> posts) {
                mProgressBar.setVisibility(View.GONE);
                postAdapter.setPostsList(posts);
                mPosts = posts;

            }
        });
    }

    private void openAddPostActivity() {
        Intent intent = new Intent(PostsActivity.this, AddPostActivity.class);
        startActivity(intent);
    }

    @Override
    public void onPostClicked(int position) {
        Toast.makeText(getApplicationContext(), mPosts.get(position).getTitle(), Toast.LENGTH_LONG).show();
        openEditPostActivity(mPosts.get(position));
    }

    private void openEditPostActivity(Post post) {
        Intent intent = new Intent(PostsActivity.this, AddPostActivity.class);
        intent.putExtra(ExtraConstants.EXTRA_POST_CONTENT, post);
        startActivity(intent);
    }
}
