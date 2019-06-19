package com.example.loginapp.landing_activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.BuildConfig;
import com.example.loginapp.R;
import com.example.loginapp.posts.post_activity.PostsActivity;

public class LandingActivity extends AppCompatActivity {

    private String email;
    private Button mButton;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        textView = findViewById(R.id.tv_email_landing_activity);
        mButton = findViewById(R.id.btn_ok_landing_activity);


        if (getIntent() != null) {
            email = getIntent().getStringExtra("email");
        }

        if (BuildConfig.FLAVOR == "free") {
            textView.setText(String.format(getString(R.string.welcome_message), email));
        }

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Ok", Toast.LENGTH_LONG).show();
                openPostsActivity();
            }
        });
    }

    private void openPostsActivity(){
        Intent intent = new Intent(LandingActivity.this, PostsActivity.class);
        startActivity(intent);
    }
}
