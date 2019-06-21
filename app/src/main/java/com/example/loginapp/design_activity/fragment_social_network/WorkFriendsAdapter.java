package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class WorkFriendsAdapter extends RecyclerView.Adapter<WorkFriendsAdapter.FriendsWorkViewHolder>{


    @NonNull
    @Override
    public FriendsWorkViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.test;
        View view = inflater.inflate(layoutId,parent,false);
        FriendsWorkViewHolder friendsWorkViewHolder = new FriendsWorkViewHolder(view);
        return friendsWorkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FriendsWorkViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class FriendsWorkViewHolder extends RecyclerView.ViewHolder {

        public FriendsWorkViewHolder (View view){
            super(view);
        }
    }
}
