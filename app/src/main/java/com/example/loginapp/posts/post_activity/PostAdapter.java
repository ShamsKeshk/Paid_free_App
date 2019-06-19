package com.example.loginapp.posts.post_activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginapp.R;
import com.example.loginapp.posts.Posts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostsViewHolder> {

    private List<Posts> postsList;

    private OnPostsListItemClicked onPostsListItemClicked;

    public interface OnPostsListItemClicked{
        void onPostClicked(int position);
    }

    public PostAdapter(List<Posts> posts,OnPostsListItemClicked onPostsListItemClicked){
        this.postsList = posts;
        this.onPostsListItemClicked = onPostsListItemClicked;
    }

    @NonNull
    @Override
    public PostsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int listItemLayoutId = R.layout.list_item_posts;
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(listItemLayoutId,parent,false);
        PostsViewHolder postsViewHolder = new PostsViewHolder(view);
        return postsViewHolder;
    }

    private Posts getItem(int position){
        return postsList.get(position);
    }

    public void setPostsList(List<Posts> postsList){
        this.postsList = postsList;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull PostsViewHolder holder, int position) {
        holder.onBind(getItem(position));
        holder.onClick(position);
       // holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        if (postsList != null && postsList.size() > 0) return postsList.size();
        return 0;
    }

    public class PostsViewHolder extends RecyclerView.ViewHolder // implements View.OnClickListener {
    {
        @BindView(R.id.tv_post_title_list_id)
        TextView tvPostsName;
        @BindView(R.id.tv_post_id_list_id)
        TextView tvPostId;
        @BindView(R.id.tv_post_description_list_id)
        TextView tvPostsDescription;

        public PostsViewHolder(View view){
            super(view);
            ButterKnife.bind(this,view);
         //   view.setOnClickListener(this::onClick);
        }
        void onBind(Posts posts){
            tvPostsName.setText(posts.getTitle());
            tvPostId.setText(" " +posts.getId());
            tvPostsDescription.setText(" " +posts.getBody());
        }

        void onClick(int position){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onPostsListItemClicked.onPostClicked(position);
                }
            });
        }
/*
        @Override
        public void onClick(View view) {
            int position = (int) view.getTag();
            onPostsListItemClicked.onPostClicked(position);
        }
        */
    }
}
