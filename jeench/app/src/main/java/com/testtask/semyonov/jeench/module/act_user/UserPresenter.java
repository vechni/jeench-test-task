package com.testtask.semyonov.jeench.module.act_user;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.module.act_user.UserContract.View;

import javax.inject.Inject;

public class UserPresenter
        extends BasePresenter<View>
        implements UserContract.Presenter
{
    public static final String TAG = UserPresenter.class.getSimpleName();

    private View view;
    @Inject DataLayer dataLayer;

    UserPresenter(){
        getPresenterComponent().inject(this);
    }

    @Override
    public void attachView( @NonNull final View view ){
        super.attachView(view);
        this.view = view;
    }

    @Override
    public void onStart(){
        super.onStart();
        view.showAlbumsView();
    }

    @Override
    public void onButtonAlbumsClicked(){
        view.showAlbumsView();
    }

    @Override
    public void onButtonPostsClicked(){
        view.showPostsView();
    }
}