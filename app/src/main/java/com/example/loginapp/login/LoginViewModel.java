package com.example.loginapp.login;


import android.view.View;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LoginViewModel extends ViewModel {


    public MutableLiveData<String> mEmailAddress = new MutableLiveData<>();
    public MutableLiveData<String> mPassword = new MutableLiveData<>();

    private MutableLiveData<LoginUser> mLoginUser;

    public MutableLiveData<LoginUser> getUser(){
        if (mLoginUser == null){
                mLoginUser = new MutableLiveData<>();
        }

        return mLoginUser;
    }

    public void onClick(View view){
        LoginUser loginUser = new LoginUser(mEmailAddress.getValue(),mPassword.getValue());
        mLoginUser.setValue(loginUser);
     }





}
