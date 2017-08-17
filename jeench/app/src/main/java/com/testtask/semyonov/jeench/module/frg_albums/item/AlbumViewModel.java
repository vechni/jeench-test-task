package com.testtask.semyonov.jeench.module.frg_albums.item;

import android.support.annotation.NonNull;

public class AlbumViewModel
{
    private int id;
    @NonNull private String title;

    public AlbumViewModel( final int id, @NonNull final String title ){
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
