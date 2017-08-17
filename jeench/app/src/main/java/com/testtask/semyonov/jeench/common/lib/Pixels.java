package com.testtask.semyonov.jeench.common.lib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.TypedValue;

public class Pixels
{
    public static float toDip( @NonNull final Context context, final int pix ){
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, pix, context.getResources().getDisplayMetrics());
    }
}
