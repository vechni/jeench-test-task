package com.testtask.semyonov.jeench.common.mvp;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.testtask.semyonov.jeench.di.component.ComponentFragment;
import com.testtask.semyonov.jeench.di.component.DaggerComponentFragment;
import com.testtask.semyonov.jeench.di.module.ModuleFragment;
import com.testtask.semyonov.jeench.module.UiRouter;

import javax.inject.Inject;

public abstract class BaseFragment
        extends Fragment
        implements MvpBaseView
{
    protected Activity activity;
    private ComponentFragment component;
    private ProgressDialog progressDialog;
    @Inject protected UiRouter uiRouter;

    @CallSuper
    @Override
    public void onCreate( Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        activity = getActivity();
        getComponent().inject(this);
    }

    public ComponentFragment getComponent(){
        if( component == null ){
            component = DaggerComponentFragment.builder()
                    .moduleFragment(new ModuleFragment(this))
                    .componentActivity(((BaseActivity) activity).getComponent())
                    .build();
        }

        return component;
    }

    public void openWaitDialog( @NonNull final String message, @NonNull final OnCancelListener listener ){
        closeWaitDialog();

        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage(message);
        progressDialog.setOnCancelListener(listener);
        progressDialog.setCancelable(true);
        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }

    public void closeWaitDialog(){
        if( progressDialog != null && progressDialog.isShowing() ){
            progressDialog.dismiss();
        }
    }

    public void showToastShort( @NonNull final String message ){
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong( @NonNull final String message ){
        Toast.makeText(activity, message, Toast.LENGTH_LONG)
                .show();
    }

    public void showToastLong( @StringRes final int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    public void showToastShort( @StringRes final int resId ){
        Toast.makeText(activity, getString(resId), Toast.LENGTH_LONG).show();
    }

    public void hideKeyboard(){
        View view = activity.getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
