package com.sty.ne.modularlearning.personal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sty.ne.common.RecordPathManager;
import com.sty.ne.common.base.BaseActivity;
import com.sty.ne.common.utils.Constants;

public class Personal_MainActivity extends BaseActivity {
    private Button btnHome;
    private Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.personal_activity_main);
        Log.e(Constants.TAG, "personal/Personal_MainActivity");

        initView();
    }

    private void initView() {
        btnOrder = findViewById(R.id.btn_order);
        btnHome = findViewById(R.id.btn_home);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnOrderClicked();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnHomeClicked();
            }
        });
    }

    private void onBtnOrderClicked() {
        //类加载方式,特别容易出现人为失误，维护成本高
//        try {
//            Class targetClass = Class.forName("com.sty.ne.modularlearning.order.Order_MainActivity");
//            Intent intent = new Intent(this, targetClass);
//            intent.putExtra("name", "sty");
//            startActivity(intent);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //全局Map记录
        Class<?> targetClass = RecordPathManager.getTargetClass("order", "Order_MainActivity");
        if(targetClass == null) {
            Log.e(Constants.TAG, "获取targetClass为空");
        }
        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "sty");
        startActivity(intent);
    }

    private void onBtnHomeClicked() {
        //类加载方式,特别容易出现人为失误，维护成本高
//        try {
//            Class targetClass = Class.forName("com.sty.ne.modularlearning.MainActivity");
//            Intent intent = new Intent(this, targetClass);
//            intent.putExtra("name", "sty");
//            startActivity(intent);
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        //全局Map记录
        Class<?> targetClass = RecordPathManager.getTargetClass("app", "MainActivity");
        if(targetClass == null) {
            Log.e(Constants.TAG, "获取targetClass为空");
        }
        Intent intent = new Intent(this, targetClass);
        intent.putExtra("name", "sty");
        startActivity(intent);
    }
}
