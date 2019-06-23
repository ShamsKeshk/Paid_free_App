package com.example.loginapp.design_activity.fragment_social_network;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

    @BindView(R.id.rv_notifications_list)
    RecyclerView rvNotificationList;

    private ProfileCardAdapter mProfileCardAdapter;
    private WorkFriendsAdapter mWorkFriendsAdapter;
    private FamilyMembersAdapter mFamilyMembersAdapter;
    private NotificationsAdapter mNotificationsAdapter;

    private int mTouchRecyclerViewTag;


    public SocialNetworkFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_social_network, container, false);
        ButterKnife.bind(this, view);


        View layout = view.findViewById(R.id.cl_rv_family_members_id);
        View layout2 = view.findViewById(R.id.cl_rv_work_friends_id);

        TextView textView = layout.findViewById(R.id.tv_rv_work_family_members_id);
        TextView textView1 = layout2.findViewById(R.id.tv_rv_work_family_members_id);

        textView1.setText("Work");

        textView.setText("Family");

        mNotificationsAdapter = new NotificationsAdapter();

        mProfileCardAdapter = new ProfileCardAdapter();

        mWorkFriendsAdapter = new WorkFriendsAdapter();

        mFamilyMembersAdapter = new FamilyMembersAdapter();

        initRecyclerView(rvUserProfileImages, initLinearLayout(false));
        rvUserProfileImages.setAdapter(mProfileCardAdapter);

        initRecyclerView(rvWorkUsersList, initLinearLayout(true));
        rvWorkUsersList.setAdapter(mWorkFriendsAdapter);

        initRecyclerView(rvFamilyMembers, initLinearLayout(true));
        rvFamilyMembers.setAdapter(mFamilyMembersAdapter);

        initRecyclerView(rvNotificationList, initLinearLayout(false));
        rvNotificationList.setAdapter(mNotificationsAdapter);

        //in case you want Two Recyclers Scrolled async ,
        // remove the Nested Scroll View From xml and this will rum correctly
        setTagsToRecyclerView();
        addListenersToRecyclers(initScrollListener(view));
        addTouchListenersToRecyclers(initRecyclerViewTouchListener());

        return view;
    }

    private LinearLayoutManager initLinearLayout(boolean isVertical) {
        if (isVertical) {
            return new LinearLayoutManager(getContext());
        } else {
            return new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        }
    }

    private void initRecyclerView(RecyclerView recyclerView, LinearLayoutManager linearLayoutManager) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    private void setTagsToRecyclerView() {
        rvWorkUsersList.setTag(0);
        rvFamilyMembers.setTag(1);
    }

    private void addListenersToRecyclers(RecyclerView.OnScrollListener onScrollListener) {
        rvWorkUsersList.addOnScrollListener(onScrollListener);
        rvFamilyMembers.addOnScrollListener(onScrollListener);
    }

    private void addTouchListenersToRecyclers(RecyclerView.OnItemTouchListener onItemTouchListener) {
        rvWorkUsersList.addOnItemTouchListener(onItemTouchListener);
        rvFamilyMembers.addOnItemTouchListener(onItemTouchListener);
    }

    private RecyclerView.OnScrollListener initScrollListener(View viewContainer) {
        RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if ((int) recyclerView.getTag() == mTouchRecyclerViewTag) {
                    for (int numOfRecyclerView = 0; numOfRecyclerView < 2; numOfRecyclerView++) {
                        if (numOfRecyclerView != (int) recyclerView.getTag()) {
                            RecyclerView tempRecyclerView = viewContainer.findViewWithTag(numOfRecyclerView);
                            tempRecyclerView.scrollBy(dx, dy);
                        }
                    }
                }
            }
        };
        return mOnScrollListener;
    }

    private RecyclerView.OnItemTouchListener initRecyclerViewTouchListener() {
        RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                mTouchRecyclerViewTag = (int) rv.getTag();
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        };
        return onItemTouchListener;
    }


}
