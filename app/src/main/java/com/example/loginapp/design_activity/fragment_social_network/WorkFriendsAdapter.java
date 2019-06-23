package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class WorkFriendsAdapter extends RecyclerView.Adapter<WorkFriendsAdapter.WorkFriendsViewHolder> {


    @NonNull
    @Override
    public WorkFriendsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.list_item_family_work_friends_members;
        View view = inflater.inflate(layoutId, parent, false);
        WorkFriendsViewHolder friendsWorkViewHolder = new WorkFriendsViewHolder(view);
        return friendsWorkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WorkFriendsViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class WorkFriendsViewHolder extends RecyclerView.ViewHolder {

        public WorkFriendsViewHolder(View view) {
            super(view);

        }
    }
}
