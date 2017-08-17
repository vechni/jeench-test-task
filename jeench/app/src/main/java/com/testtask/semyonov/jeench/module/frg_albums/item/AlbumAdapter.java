package com.testtask.semyonov.jeench.module.frg_albums.item;

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

public class AlbumAdapter
        extends RecyclerView.Adapter<AlbumAdapter.AlbumHolder>
{
    private Listener listener;
    private List<AlbumViewModel> dataSet = new ArrayList<>();

    public AlbumAdapter( @NonNull final Listener listener ){
        this.listener = listener;
    }

    public void setItems( @NonNull final List<AlbumViewModel> dataSet ){
        this.dataSet = dataSet;
    }

    @Override
    public AlbumHolder onCreateViewHolder( final ViewGroup parent, final int viewType ){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new AlbumHolder(view);
    }

    @Override
    public void onBindViewHolder( final AlbumHolder holder, final int position ){
        final AlbumViewModel model = dataSet.get(position);
        holder.tvTitle.setText(model.getTitle());
        holder.itemView.setOnClickListener(v->listener.onAlbumClicked(model));
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    public interface Listener
    {
        void onAlbumClicked( @NonNull AlbumViewModel model );
    }


    class AlbumHolder
            extends RecyclerView.ViewHolder
    {
        @BindView( R.id.item_product_txt_title ) TextView tvTitle;

        private AlbumHolder( @NonNull final View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}