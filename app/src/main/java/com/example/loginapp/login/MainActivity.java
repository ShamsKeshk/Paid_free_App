package com.example.loginapp.login;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);


        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if (loginUser != null && ValidateUtil.getInstance().validate(loginUser, binding)) {
                    Toast.makeText(getApplicationContext(), "Email :" + loginUser.getEmail(), Toast.LENGTH_LONG).show();
                    open(loginUser);
                }
            }
        });
    }

    private void open(LoginUser loginUser) {
       /* Intent intent = new Intent(MainActivity.this, LandingActivity.class);
        intent.putExtra("email",loginUser.getEmail());
        startActivity(intent); */

        Intent intent = new Intent();
        String packageName = getApplicationContext().getPackageName();
        ComponentName componentName = new ComponentName(packageName, packageName + ".aliasMain");
        intent.setComponent(componentName);
        intent.putExtra("email", loginUser.getEmail());
        startActivity(intent);
    }

}
