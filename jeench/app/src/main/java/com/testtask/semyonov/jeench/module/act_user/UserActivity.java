package com.testtask.semyonov.jeench.module.act_user;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.interfaces.OnBackPressedListener;
import com.testtask.semyonov.jeench.common.mvp.BaseActivity;
import com.testtask.semyonov.jeench.utils.AppConst;
import com.testtask.semyonov.jeench.utils.AppKeys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserActivity
        extends BaseActivity
        implements UserContract.View
{
    private final String TAG = UserActivity.class.getSimpleName();

    @BindView( R.id.activity_users_detail_txt_name ) TextView tvName;
    @BindView( R.id.activity_users_detail_txt_email ) TextView tvEmail;
    @BindView( R.id.activity_users_detail_txt_address ) TextView tvAddress;
    @BindView( R.id.activity_users_detail_btn_albums ) RadioButton btnAlbums;
    @BindView( R.id.activity_users_detail_btn_posts ) RadioButton btnPosts;
    @BindView( R.id.activity_users_detail_frame ) FrameLayout containerFrame;
    private String name = AppConst.EMPTY_STRING;
    private String email = AppConst.EMPTY_STRING;
    private String address = AppConst.EMPTY_STRING;
    private int userId = AppConst.DEFAULT_ID;
    private UserPresenter presenter;

    @Override
    protected void onCreate( final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        presenter = new UserPresenter();
        presenter.attachView(this);

        ButterKnife.bind(this);

        final Bundle extras = getIntent().getExtras();
        if( extras != null ){
            name = extras.getString(AppKeys.NAME, AppConst.EMPTY_STRING);
            email = extras.getString(AppKeys.EMAIL, AppConst.EMPTY_STRING);
            address = extras.getString(AppKeys.ADDRESS, AppConst.EMPTY_STRING);
            userId = extras.getInt(AppKeys.USER_ID, AppConst.DEFAULT_ID);
        }

        tvName.append(name);
        tvEmail.append(email);
        tvAddress.append(address);
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

    @Override
    public void onBackPressed(){
        final Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.activity_users_detail_frame);

        if( currentFragment instanceof OnBackPressedListener && currentFragment.isVisible() ){
            final OnBackPressedListener backPressedListener = (OnBackPressedListener) currentFragment;
            backPressedListener.onBackPressed();
            return;
        }

        super.onBackPressed();
    }

    @OnClick( R.id.activity_users_detail_btn_albums )
    public void onButtonAlbumsClicked( final View view ){
        presenter.onButtonAlbumsClicked();
    }

    @OnClick( R.id.activity_users_detail_btn_posts )
    public void onButtonPostsClicked( final View view ){
        presenter.onButtonPostsClicked();
    }

    @Override
    public void showAlbumsView(){
        uiRouter.openAlbumsView(userId);
    }

    @Override
    public void showPostsView(){
        uiRouter.openPostsView(userId);
    }
}
