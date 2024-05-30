package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Set;

public class Setting extends AppCompatActivity {
    TextView tvBack;

    Button btnThemChuDe,btnDangXuat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setControl();
        setEvent();
    }
    public void setControl(){
        tvBack = findViewById(R.id.setting_back);
        btnThemChuDe = findViewById(R.id.setting_themchude);
        btnDangXuat = findViewById(R.id.setting_dangxuat);
    }
    public void setEvent(){
        btnThemChuDe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this,ChuDe1.class);
                startActivity(intent);
            }
        });
        tvBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}