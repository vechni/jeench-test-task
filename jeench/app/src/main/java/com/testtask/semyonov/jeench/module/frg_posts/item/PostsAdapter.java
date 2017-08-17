package com.testtask.semyonov.jeench.module.frg_posts.item;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.testtask.semyonov.jeench.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PostsAdapter
        extends RecyclerView.Adapter<PostsAdapter.PostHolder>
{
    private List<PostViewModel> dataSet = new ArrayList<>();

    public PostsAdapter(){

    }

    public void setItems( @NonNull final List<PostViewModel> dataSet ){
        this.dataSet = dataSet;
    }

    @Override
    public PostsAdapter.PostHolder onCreateViewHolder( final ViewGroup parent, final int viewType ){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new PostsAdapter.PostHolder(view);
    }

    @Override
    public void onBindViewHolder( final PostsAdapter.PostHolder holder, final int position ){
        final PostViewModel model = dataSet.get(position);
        holder.tvTitle.setText(model.getTitle());
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    class PostHolder
            extends RecyclerView.ViewHolder
    {
        @BindView( R.id.item_product_txt_title ) TextView tvTitle;

        private PostHolder( @NonNull final View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}