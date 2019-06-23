package com.example.loginapp.design_activity.fragment_social_network;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;

public class FamilyMembersAdapter extends RecyclerView.Adapter<FamilyMembersAdapter.FamilyMembersViewHolder> {

    @NonNull
    @Override
    public FamilyMembersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        int layoutId = R.layout.list_item_family_work_friends_members;
        View view = inflater.inflate(layoutId, parent, false);
        FamilyMembersViewHolder friendsWorkViewHolder = new FamilyMembersViewHolder(view);
        return friendsWorkViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FamilyMembersViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class FamilyMembersViewHolder extends RecyclerView.ViewHolder {

        public FamilyMembersViewHolder(View view) {
            super(view);

        }
    }
}
