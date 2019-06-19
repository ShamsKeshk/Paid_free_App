package com.example.loginapp.common;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecutors {

    private Executor networkIo;
    private Executor diskIo;
    private Executor mainThread;

    private static AppExecutors sAppExecutors;
    private static final Object LOCK = new Object();


    private AppExecutors(Executor networkIo,Executor diskIo,Executor mainThread){
        this.networkIo = networkIo;
        this.diskIo = diskIo;
        this.mainThread = mainThread;
    }

    public static AppExecutors getInstance(){
        if (sAppExecutors == null){
            synchronized (LOCK){
                sAppExecutors = new AppExecutors(Executors.newFixedThreadPool(3),
                        Executors.newSingleThreadExecutor(),new MainThreadExecutor());
            }
        }
        return sAppExecutors;
    }

    private static class MainThreadExecutor implements Executor {

        final Handler mHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mHandler.post(runnable);
        }
    }

    public Executor getNetworkIo() {
        return networkIo;
    }

    public Executor getDiskIo() {
        return diskIo;
    }

    public Executor getMainThread() {
        return mainThread;
    }
}
