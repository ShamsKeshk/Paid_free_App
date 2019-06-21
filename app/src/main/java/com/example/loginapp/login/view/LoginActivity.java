package com.example.loginapp.login.view;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.loginapp.R;
import com.example.loginapp.databinding.ActivityLoginBinding;
import com.example.loginapp.login.utils.ValidateUtil;
import com.example.loginapp.login.model.LoginUser;
import com.example.loginapp.login.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;

    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        binding = DataBindingUtil.setContentView(LoginActivity.this, R.layout.activity_login);

        binding.setLifecycleOwner(this);

        binding.setLoginViewModel(loginViewModel);

        LoginUser loginUser = new LoginUser("shams.keshk@gmail.com","kajsdfasdfadf");

        open(loginUser);
/*
        loginViewModel.getUser().observe(this, new Observer<LoginUser>() {
            @Override
            public void onChanged(LoginUser loginUser) {
                if (loginUser != null && ValidateUtil.getInstance().validate(loginUser, binding)) {
                    Toast.makeText(getApplicationContext(), "Email :" + loginUser.getEmail(), Toast.LENGTH_LONG).show();
                    open(loginUser);
                }
            }
        });
        */
    }

    private void open(LoginUser loginUser) {
       /* Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
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
