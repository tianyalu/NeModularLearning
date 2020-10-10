package com.sty.ne.common.base;

import android.app.Application;
import android.util.Log;

import com.sty.ne.common.utils.Constants;

/**
 * @Author: tian
 * @UpdateDate: 2020/10/10 9:45 PM
 */
public class BaseApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(Constants.TAG, "common/BaseApplication");
    }
}
