package com.example.loginapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.loginapp.posts.post_activity.Post;

import java.util.List;

@Dao
public interface PostsDao {

    @Query("SELECT p.id,p.userId,p.title,p.body FROM posts p ")
    LiveData<List<Post>> getPosts();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long createPost(Post post);

    @Query("SELECT p.id,p.userId,p.title,p.body FROM posts p WHERE p.id = :id")
    LiveData<Post> getPostById(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void bulkInsert(List<Post> posts);
}
