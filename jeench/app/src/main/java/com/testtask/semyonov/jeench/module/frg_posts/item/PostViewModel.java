package com.testtask.semyonov.jeench.module.frg_posts.item;

import android.support.annotation.NonNull;

public class PostViewModel
{
    private int id;
    @NonNull private String title;

    public PostViewModel( final int id, @NonNull final String title ){
        this.id = id;
        this.title = title;
    }

    @NonNull
    public String getTitle(){
        return title;
    }

    public int getId(){
        return id;
    }
}
