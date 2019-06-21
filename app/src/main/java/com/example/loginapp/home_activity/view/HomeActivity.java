package com.example.loginapp.home_activity.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.BuildConfig;
import com.example.loginapp.R;
import com.example.loginapp.design_activity.DesignActivity;
import com.example.loginapp.posts.post_activity.view.PostsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    private String email;

    @BindView(R.id.tv_email_landing_activity)
    TextView textView;

    @BindView(R.id.btn_ok_landing_activity)
    Button mButton;

    @BindView(R.id.btn_open_design_activity_id)
    Button mButtonOpenDesignActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        if (getIntent() != null) {
            email = getIntent().getStringExtra("email");
        }

        if (BuildConfig.FLAVOR == "free") {
            textView.setText(String.format(getString(R.string.welcome_message), email));
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPostsActivity();
            }
        });

         mButtonOpenDesignActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDesignActivity();
             //   openDesignActivity();
            }
        });
    }

    private void openPostsActivity() {
        Intent intent = new Intent(HomeActivity.this, PostsActivity.class);
        startActivity(intent);
    }

    private void openDesignActivity(){
        Intent intent = new Intent(HomeActivity.this, DesignActivity.class);
        startActivity(intent);
    }
}
