package com.testtask.semyonov.jeench.module.act_users;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.lib.Dips;
import com.testtask.semyonov.jeench.common.mvp.BaseActivity;
import com.testtask.semyonov.jeench.module.act_users.item.UserViewModel;
import com.testtask.semyonov.jeench.module.act_users.item.UsersAdapter;
import com.testtask.semyonov.jeench.module.act_users.item.UsersAdapter.Listener;
import com.testtask.semyonov.jeench.views.SpacesItemDecoration;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UsersActivity
        extends BaseActivity
        implements UsersContract.View
{
    private final String TAG = UsersActivity.class.getSimpleName();

    @BindView( R.id.activity_list_rv ) RecyclerView recyclerView;
    @BindView( R.id.activity_list_swipe_refresh ) SwipeRefreshLayout swipeContainer;
    private UsersAdapter adapter;
    private UsersPresenter presenter;

    @Override
    protected void onCreate( final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        presenter = new UsersPresenter();
        presenter.attachView(this);

        ButterKnife.bind(this);

        adapter = new UsersAdapter(listener);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new SpacesItemDecoration((int) Dips.toPix(this, 10), 0, 0, 0));
        recyclerView.setAdapter(adapter);

        swipeContainer.setOnRefreshListener(()->presenter.onRefresh());
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light
        );
    }

    @Override
    protected void onStart(){
        super.onStart();
        presenter.onStart();
    }

    @Override
    protected void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    protected void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    protected void onStop(){
        super.onStop();
        presenter.onStop();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.detachView();
    }

    UsersAdapter.Listener listener = new Listener()
    {
        @Override
        public void onUserClicked( @NonNull final UserViewModel model ){
            presenter.onClickItemUser(model);
        }
    };

    @Override
    public void updateUsers( @NonNull final List<UserViewModel> items ){
        adapter.setItems(items);
        adapter.notifyDataSetChanged();
        swipeContainer.setRefreshing(false);
    }

    @Override
    public void navigateToUserScreen( @NonNull final UserViewModel model ){
        uiRouter.openUserView(model.getId(), model.getName(), model.getEmail(), model.getAddress());
    }

    @Override
    public void hideLoadIndicator(){
        swipeContainer.setRefreshing(false);
    }
}

