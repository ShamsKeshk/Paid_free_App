package com.example.loginapp.design_activity.fragment_social_network;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.loginapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialNetworkFragment extends Fragment {

    @BindView(R.id.rv_list_of_users_profiles)
    RecyclerView rvUserProfileImages;
    @BindView(R.id.rv_list_of_work_friends_id)
    RecyclerView rvWorkUsersList;
    @BindView(R.id.rv_list_of_family_members_id)
    RecyclerView rvFamilyMembers;

    private ProfileCardAdapter mProfileCardAdapter;
    private WorkFriendsAdapter mWorkFriendsAdapter;
    private FamilyMembersAdapter mFamilyMembersAdapter;


    public SocialNetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_network, container, false);
        ButterKnife.bind(this,view);

        View layout = view.findViewById(R.id.cl_rv_family_members_id);
        View layout2 = view.findViewById(R.id.cl_rv_work_friends_id);

        TextView textView = layout.findViewById(R.id.tv_rv_work_family_members_id);
        TextView textView1 = layout2.findViewById(R.id.tv_rv_work_family_members_id);

        textView1.setText("Work");

        textView.setText("Family");

        mProfileCardAdapter = new ProfileCardAdapter();

        mWorkFriendsAdapter = new WorkFriendsAdapter();

        //FIXME , Why should We Create Another Adapter With Same Implementation
       // mFamilyMembersAdapter = new FamilyMembersAdapter();

        initRecyclerView(rvUserProfileImages,initLinearLayout(false));
        rvUserProfileImages.setAdapter(mProfileCardAdapter);

        initRecyclerView(rvWorkUsersList,initLinearLayout(true));
        rvWorkUsersList.setAdapter(mWorkFriendsAdapter);

        initRecyclerView(rvFamilyMembers,initLinearLayout(true));
        rvFamilyMembers.setAdapter(mWorkFriendsAdapter);

        return view;
    }

    private LinearLayoutManager initLinearLayout(boolean isVertical){
        if (isVertical){
            return new LinearLayoutManager(getContext());
        }else {
           return new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        }
    }

    private void initRecyclerView(RecyclerView recyclerView,LinearLayoutManager linearLayoutManager){
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

}
