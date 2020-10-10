package com.sty.ne.common.base;

import android.os.Bundle;
import android.util.Log;

import com.sty.ne.common.utils.Constants;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author: tian
 * @UpdateDate: 2020/10/10 9:48 PM
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e(Constants.TAG, "common/BaseActivity");
    }
}
