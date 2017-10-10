package com.testtask.semyonov.jeench.module.dialog_post;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.testtask.semyonov.jeench.R;
import com.testtask.semyonov.jeench.common.mvp.BaseDialogFragment;
import com.testtask.semyonov.jeench.utils.AppConst;
import com.testtask.semyonov.jeench.utils.AppKeys;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PostDialog
        extends BaseDialogFragment
        implements PostContract.View
{
    private final String TAG = PostDialog.class.getSimpleName();

    @BindView( R.id.dialog_post_edit_text_title ) EditText etTitle;
    @BindView( R.id.dialog_post_edit_text_body ) EditText etBody;
    private PostPresenter presenter;
    private int userId = AppConst.DEFAULT_ID;

    @Override
    public void onCreate( @Nullable final Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if( args != null ){
            userId = args.getInt(AppKeys.USER_ID, AppConst.DEFAULT_ID);
        }
        presenter = new PostPresenter(userId);
        presenter.attachView(this);
    }

    @Override
    public View onCreateView( @NonNull final LayoutInflater inflater,
                              @Nullable final ViewGroup container,
                              @Nullable final Bundle savedInstanceState ){
        final View root = inflater.inflate(R.layout.dialog_add_post, container, false);
        ButterKnife.bind(this, root);

        return root;
    }

    @Override
    public void onStart(){
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onResume(){
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause(){
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        presenter.detachView();
    }

    @OnClick( R.id.dialog_post_btn_add_post )
    public void onButtonAddPostClicked( @NonNull final View view ){
        final String title = etTitle.getText().toString();
        final String body = etBody.getText().toString();
        if( title.isEmpty() || body.isEmpty() ){
            showToastShort(R.string.msg_enter_data);
            return;
        }
        presenter.onClickBtnConfirmAddPost(title, body);
    }

    @OnClick( R.id.dialog_post_btn_cancel )
    public void onCancelClicked( @NonNull final View view ){
        presenter.onClickBtnCancel();
    }

    @Override
    public void closeDialog(){
        dismiss();
    }
}