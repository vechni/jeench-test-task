package com.testtask.semyonov.jeench.module.act_users.item;

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

public class UsersAdapter
        extends RecyclerView.Adapter<UsersAdapter.UserHolder>
{
    private Listener listener;
    private List<UserViewModel> dataSet = new ArrayList<>();

    public UsersAdapter( @NonNull final Listener listener ){
        this.listener = listener;
    }

    public void setItems( @NonNull final List<UserViewModel> dataSet ){
        this.dataSet = dataSet;
    }

    @Override
    public UserHolder onCreateViewHolder( final ViewGroup parent, final int viewType ){
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserHolder(view);
    }

    @Override
    public void onBindViewHolder( final UserHolder holder, final int position ){
        final UserViewModel model = dataSet.get(position);
        holder.tvName.append(model.getName());
        holder.tvEmail.append(model.getEmail());
        holder.tvAddress.append(model.getAddress());
        holder.itemView.setOnClickListener(v->listener.onUserClicked(model));
    }

    @Override
    public int getItemCount(){
        return dataSet.size();
    }

    public interface Listener
    {
        void onUserClicked( @NonNull UserViewModel model );
    }


    class UserHolder
            extends RecyclerView.ViewHolder
    {
        @BindView( R.id.item_user_txt_name ) TextView tvName;
        @BindView( R.id.item_user_txt_email ) TextView tvEmail;
        @BindView( R.id.item_user_txt_address ) TextView tvAddress;

        private UserHolder( @NonNull final View view ){
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
