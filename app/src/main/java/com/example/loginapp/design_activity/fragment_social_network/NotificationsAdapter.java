package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {


    @NonNull
    @Override
    public NotificationsAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.list_item_notification;
        View view = inflater.inflate(layoutId,parent,false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.NotificationViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {


        public NotificationViewHolder (View view){
            super(view);
        }
    }
}
