package com.example.loginapp.paid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginapp.R;
import com.example.loginapp.landing_activity.post_activity.PostsActivity;

public class LandingActivity extends com.example.loginapp.landing_activity.LandingActivity {

    private String mEmail;

    private TextView mTextView;

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        mTextView = findViewById(R.id.tv_email_landing_activity);
        mButton = findViewById(R.id.btn_ok_landing_activity);

        if (getIntent() != null){
            mEmail = getIntent().getStringExtra("email");
        }

        mTextView.setText(mEmail);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Ok",Toast.LENGTH_LONG).show();
                openPostsActivity();
            }
        });
    }

    private void openPostsActivity(){
        Intent intent = new Intent(LandingActivity.this, PostsActivity.class);
        startActivity(intent);
    }
}
