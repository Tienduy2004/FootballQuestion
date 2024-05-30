package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BatDauChoi extends AppCompatActivity {

    ListView lvDSChuDe;
    DatabaseHelper databaseHelper;
    private static List<ChuDe> chuDeList;
    public static ChuDeAdater chuDeAdater;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bat_dau_choi);
        setControl();
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Chọn chủ đề");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        databaseHelper = new DatabaseHelper(this);
        Event();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mmExit){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void setControl(){
        lvDSChuDe = findViewById(R.id.lvDSChonChuDe);
    }
    public void Event(){
        Intent intentUser = getIntent();
        idUser = intentUser.getIntExtra("idUser", -1);
        chuDeList = databaseHelper.getAllChuDe();
        if (chuDeList == null) {
            chuDeList = new ArrayList<>();
        }
        chuDeAdater = new ChuDeAdater(BatDauChoi.this,R.layout.dong_chude,chuDeList);
        lvDSChuDe.setAdapter(chuDeAdater);
        lvDSChuDe.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(BatDauChoi.this,CauHoiChoi.class);
                intent.putExtra("idchude",chuDeList.get(position).getID());
                intent.putExtra("idUser",idUser);
                startActivity(intent);
            }
        });
    }
    public static List<ChuDe> getChuDe() {
        return chuDeList;
    }
}