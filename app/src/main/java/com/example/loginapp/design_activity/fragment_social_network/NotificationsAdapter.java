package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private Context mContext;

    @NonNull
    @Override
    public NotificationsAdapter.NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        int layoutId = R.layout.list_item_notification;
        View view = inflater.inflate(layoutId, parent, false);
        NotificationViewHolder notificationViewHolder = new NotificationViewHolder(view);
        return notificationViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationsAdapter.NotificationViewHolder holder, int position) {
        if (position != 2) {
            holder.mTextViewCounter.setVisibility(View.VISIBLE);
            holder.mTextViewCounterBig.setVisibility(View.GONE);
        } else {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            params.setMargins(10, 0, 0, 0);
            holder.mFrameLayout.setLayoutParams(params);
            holder.mTextViewCounter.setVisibility(View.GONE);
            holder.mTextViewCounterBig.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.frame_notification)
        FrameLayout mFrameLayout;
        @BindView(R.id.tv_notification_counter_id)
        TextView mTextViewCounter;
        @BindView(R.id.tv_notification_counter_big_id)
        TextView mTextViewCounterBig;

        public NotificationViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
