package com.testtask.semyonov.jeench.module.dialog_post;

import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BasePresenter;
import com.testtask.semyonov.jeench.data.DataLayer;
import com.testtask.semyonov.jeench.data.exeptions.NoConnectivityException;
import com.testtask.semyonov.jeench.module.dialog_post.PostContract.View;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class PostPresenter
        extends BasePresenter<PostContract.View>
        implements PostContract.Presenter
{
    @Inject DataLayer dataLayer;
    private View view;
    private int userId;

    PostPresenter( final int userId ){
        getPresenterComponent().inject(this);
        this.userId = userId;
    }

    @Override
    public void attachView( @NonNull final PostContract.View view ){
        super.attachView(view);
        this.view = view;
    }

    @Override
    public void onConfirmAddPostClicked( @NonNull final String title, @NonNull final String body ){
        dataLayer.rest.sendPost(userId, title, body)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(response->{
                    if( response.getUserId() == userId ){
                        view.showToastShort(R.string.msg_post_added);
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                }, e->{
                    if( e instanceof NoConnectivityException ){
                        view.showToastShort(e.getMessage());
                        return;
                    }
                    view.showToastShort(R.string.msg_error_request);
                });
    }

    @Override
    public void onCancelClicked(){
        view.closeDialog();
    }
}
