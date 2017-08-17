package com.testtask.semyonov.jeench.module.act_album_detail.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.views.AlbumImageView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ContentAlbumAdapter
        extends RecyclerView.Adapter<ContentAlbumAdapter.ContentAlbumHolder>
{
    private List<ContentAlbumViewModel> dataSet = new ArrayList<>();

    public ContentAlbumAdapter(){

    }

    public void setItems( @NonNull final List<ContentAlbumViewModel> dataSet ){
        this.dataSet = dataSet;
    }

    @Override
    public ContentAlbumHolder onCreateViewHolder( final ViewGroup parent, final int viewType ){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_content_album, parent, false);
        return new ContentAlbumHolder(view);
    }

    @Override
    public void onBindViewHolder( final ContentAlbumHolder holder, final int position ){
        final ContentAlbumViewModel model = dataSet.get(position);
        holder.ivAvatar.loadImage(model.getUrl());
        holder.tvTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    class ContentAlbumHolder extends RecyclerView.ViewHolder
    {
        @BindView( R.id.item_content_album_image ) AlbumImageView ivAvatar;
        @BindView( R.id.item_content_album_title ) TextView tvTitle;

        private ContentAlbumHolder( @NonNull final View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
