package com.example.huy.androiddesignsupportlibrarydemo.util;

import android.content.Context;
import android.os.Build;

/**
 * Created by Huy on 5/14/2016.
 */
public class Utility {
    public static final int getColor(Context context, int id) {
        final int version = Build.VERSION.SDK_INT;
        if (version >= 23) {
            return getColor(context, id);
        } else {
            return context.getResources().getColor(id);
        }
    }
}
