package com.testtask.semyonov.jeench.common.mvp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface.OnCancelListener;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.testtask.semyonov.jeench.JeenchApplication;
import com.testtask.semyonov.jeench.di.component.ComponentActivity;
import com.testtask.semyonov.jeench.di.component.DaggerComponentActivity;
import com.testtask.semyonov.jeench.di.module.ModuleActivity;
import com.testtask.semyonov.jeench.module.UiRouter;

import javax.inject.Inject;

public abstract class BaseActivity
        extends AppCompatActivity
        implements MvpBaseView
{
    private ComponentActivity component;
    private ProgressDialog progressDialog;
    @Inject protected UiRouter uiRouter;

    @CallSuper
    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ){
        super.onCreate(savedInstanceState);
        getComponent().inject(this);
    }

    public ComponentActivity getComponent(){
        if( component == null ){
            component = DaggerComponentActivity.builder()
                    .moduleActivity(new ModuleActivity(this))
                    .componentApplication(JeenchApplication.getComponentApplication())
                    .build();
        }

        return component;
    }

    public void openWaitDialog( @NonNull final String message, @NonNull final OnCancelListener listener ){
        closeWaitDialog();

        progressDialog = new ProgressDialog(this);
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
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showToastLong( @NonNull final String message ){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void showToastLong( @StringRes final int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG)
                .show();
    }

    public void showToastShort( @StringRes final int resId ){
        Toast.makeText(this, getString(resId), Toast.LENGTH_LONG).show();
    }

    public void hideKeyboard(){
        View view = getCurrentFocus();

        if( view != null ){
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public void showKeyboard(){
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }
}
