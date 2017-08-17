package com.testtask.semyonov.jeench.views;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import com.bumptech.glide.Glide;
import com.testtask.semyonov.jeench.R;

public class AlbumImageView
        extends AppCompatImageView
{
    public AlbumImageView( Context context ){
        super(context);
    }

    public AlbumImageView( Context context, @Nullable AttributeSet attrs ){
        super(context, attrs);
    }

    public AlbumImageView( Context context, @Nullable AttributeSet attrs, int defStyleAttr ){
        super(context, attrs, defStyleAttr);
    }

    public void loadImage( @NonNull final String url ){
        Glide.with(getContext())
                .load(url)
                .centerCrop()
                .placeholder(R.drawable.default_image)
                .error(R.drawable.default_image)
                .dontAnimate()
                .into(this);
    }
}
