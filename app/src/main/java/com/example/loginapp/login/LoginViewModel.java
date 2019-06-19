package com.example.loginapp.login;


import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {


    public MutableLiveData<String> mEmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> mPassword = new MutableLiveData<>();

    private LoginUser loginUser;

    private MutableLiveData<LoginUser> mLoginUser;

    public MutableLiveData<LoginUser> getUser() {
        if (mLoginUser == null) {
            mLoginUser = new MutableLiveData<>();
            loginUser = new LoginUser();
        }
        return mLoginUser;
    }

    public void onClick(View view) {
        loginUser.setEmail(mEmailAddress.getValue());
        loginUser.setPassword(mPassword.getValue());
        mLoginUser.setValue(loginUser);
    }


}
