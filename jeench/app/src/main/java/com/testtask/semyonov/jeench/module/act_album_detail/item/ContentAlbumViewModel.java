package com.testtask.semyonov.jeench.module.act_album_detail.item;

import android.support.annotation.NonNull;

public class ContentAlbumViewModel
{
    @NonNull private String title;
    @NonNull private String url;

    public ContentAlbumViewModel( @NonNull final String title, @NonNull final String url ){
        this.title = title;
        this.url = url;
    }

    @NonNull
    public String getTitle(){
        return title;
    }

    @NonNull
    public String getUrl(){
        return url;
    }
}
