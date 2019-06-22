package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class ProfileCardAdapter extends  RecyclerView.Adapter<ProfileCardAdapter.UserProfileCardViewHolder> {

    @NonNull
    @Override
    public UserProfileCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int listItemLayout = R.layout.list_item_users_profile_card;
        View view = inflater.inflate(listItemLayout,parent,false);
        UserProfileCardViewHolder userProfileCardViewHolder = new UserProfileCardViewHolder(view);
        return userProfileCardViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull UserProfileCardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    public class UserProfileCardViewHolder extends RecyclerView.ViewHolder{

        public UserProfileCardViewHolder(View view){
            super(view);
        }
    }
}
