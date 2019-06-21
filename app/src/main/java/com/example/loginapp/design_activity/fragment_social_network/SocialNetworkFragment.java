package com.example.loginapp.design_activity.fragment_social_network;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.loginapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class SocialNetworkFragment extends Fragment {

    @BindView(R.id.rv_list_of_users_profiles)
    RecyclerView rvUserProfileImages;
    @BindView(R.id.rv_list_of_work_user_id)
    RecyclerView rvWorkUsersList;

    private ProfileCardAdapter mProfileCardAdapter;
    private WorkFriendsAdapter mWorkFriendsAdapter;


    public SocialNetworkFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_network, container, false);
        ButterKnife.bind(this,view);

        mProfileCardAdapter = new ProfileCardAdapter();

        mWorkFriendsAdapter = new WorkFriendsAdapter();

        LinearLayoutManager horizontalLinearLayoutManager =
                new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);

        LinearLayoutManager verticalLinearLayoutManager = new LinearLayoutManager(getContext());

        rvUserProfileImages.setHasFixedSize(true);
        rvUserProfileImages.setLayoutManager(horizontalLinearLayoutManager);
        rvUserProfileImages.setAdapter(mProfileCardAdapter);

        rvWorkUsersList.setHasFixedSize(true);
        rvWorkUsersList.setLayoutManager(verticalLinearLayoutManager);
        rvWorkUsersList.setAdapter(mWorkFriendsAdapter);

        return view;
    }

}
