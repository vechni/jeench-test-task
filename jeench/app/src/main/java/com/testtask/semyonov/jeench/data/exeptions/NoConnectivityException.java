package com.testtask.semyonov.jeench.data.exeptions;

import android.content.Context;
import android.support.annotation.NonNull;

import com.testtask.semyonov.jeench.R;

import java.io.IOException;

public class NoConnectivityException extends IOException
{
    Context context;

    public NoConnectivityException( @NonNull final Context context ){
        this.context = context;
    }

    @Override
    public String getMessage(){
        return context.getString(R.string.msg_error_no_network_connection);
    }
}
