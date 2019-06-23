package com.example.loginapp.design_activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.loginapp.R;
import com.example.loginapp.design_activity.fragment_social_network.SocialNetworkFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import butterknife.BindView;

public class DesignActivity extends AppCompatActivity {
    @BindView(R.id.fl_activity_design_container_id)
    FrameLayout mFrameLayoutContainer;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    // mTextMessage.setText(R.string.title_home);
                    openDefaultFragment();
                    return true;
                case R.id.navigation_dashboard:
                    //TODO Create Another Fragment
                    return true;
                case R.id.navigation_notifications:
                    //TODO Create Another Fragment
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_design);
        BottomNavigationView navView = findViewById(R.id.nav_view);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        openDefaultFragment();
    }

    private void openDefaultFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_activity_design_container_id, new SocialNetworkFragment()).commit();
    }

}
