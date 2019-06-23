package com.example.loginapp.common.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.loginapp.posts.post_activity.model.Post;

@Database(entities = Post.class, version = 1, exportSchema = false)
public abstract class PostsDatabase extends RoomDatabase {

    private static final Object LOCK = new Object();

    private static final String DATABASE_NAME = "posts_db";

    private static PostsDatabase sPostsDatabase;

    public static PostsDatabase getInstance(Context context) {
        if (sPostsDatabase == null) {
            synchronized (LOCK) {
                sPostsDatabase = Room.databaseBuilder(
                        context,
                        PostsDatabase.class
                        , PostsDatabase.DATABASE_NAME).build();
            }
        }
        return sPostsDatabase;
    }

    public abstract PostsDao postsDao();

}
