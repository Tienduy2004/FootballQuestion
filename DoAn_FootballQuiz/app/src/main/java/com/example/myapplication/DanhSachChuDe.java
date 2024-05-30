package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class DanhSachChuDe extends AppCompatActivity {

    ListView lvDSChuDe;
    DatabaseHelper databaseHelper;
    private static List<ChuDe> chuDeList;
    public static ChuDeAdater chuDeAdater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danh_sach_chu_de);
        lvDSChuDe = findViewById(R.id.lvDanhSachChuDe);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Quản lý chủ đề");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        databaseHelper = new DatabaseHelper(this);
        Event();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        onBackPressed();
        return super.onOptionsItemSelected(item);
    }
    public void Event(){
        chuDeList = databaseHelper.getAllChuDe();
        if (chuDeList == null) {
            chuDeList = new ArrayList<>();
        }
        chuDeAdater = new ChuDeAdater(DanhSachChuDe.this,R.layout.dong_chude,chuDeList);
        lvDSChuDe.setAdapter(chuDeAdater);
        lvDSChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = chuDeList.get(position).getTenChuDe().toString();
                Intent intent = new Intent(DanhSachChuDe.this,ChiTietChuDe.class);
                intent.putExtra("idchude",chuDeList.get(position).getID());
                intent.putExtra("tenchude",str);
                startActivity(intent);
            }
        });
    }
    public static List<ChuDe> getChuDe() {
        return chuDeList;
    }
}