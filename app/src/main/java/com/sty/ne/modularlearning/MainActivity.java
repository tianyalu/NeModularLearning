package com.sty.ne.modularlearning;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sty.ne.modularlearning.order.Order_MainActivity;
import com.sty.ne.modularlearning.personal.Personal_MainActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnOrder;
    private Button btnPersonal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btnOrder = findViewById(R.id.btn_order);
        btnPersonal = findViewById(R.id.btn_personal);
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnOrderClicked();
            }
        });
        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBtnPersonalClicked();
            }
        });
    }

    private void onBtnOrderClicked() {
        Intent intent = new Intent(this, Order_MainActivity.class);
        intent.putExtra("name", "sty");
        startActivity(intent);
    }

    private void onBtnPersonalClicked() {
        Intent intent = new Intent(this, Personal_MainActivity.class);
        intent.putExtra("name", "sty");
        startActivity(intent);
    }
}