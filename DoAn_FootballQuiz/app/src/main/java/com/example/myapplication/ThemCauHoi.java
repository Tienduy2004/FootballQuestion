package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ThemCauHoi extends AppCompatActivity {

    private ListView lvCauHoi;
    public static CauHoiAdapter adapter;
    public static List<CauHoi> cauHoiList;
    private DatabaseHelper databaseHelper;
    public static int chuDeID;
    private int idUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_cau_hoi);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Danh sách câu hỏi");
        if (actionBar != null) {
            actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.xanhla)));
        }
        setControl();
        setEvent();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cauhoi, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.mmThem){
            Intent intent = new Intent(ThemCauHoi.this, CauHoiThem.class);
            intent.putExtra("ChuDeID",chuDeID);
            startActivity(intent);
        }
        if (item.getItemId()==R.id.mmThoat){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void setControl() {
        lvCauHoi = findViewById(R.id.lvCauHoi);
    }

    private void setEvent() {
        Intent intent = getIntent();
        chuDeID = intent.getIntExtra("ChuDeID", -1);
        idUser = intent.getIntExtra("idUser", -1);
        if (chuDeID != -1) {
            // Sử dụng chuDeID để hiển thị danh sách câu hỏi
            if (cauHoiList == null) {
                cauHoiList = new ArrayList<>();
            }
            databaseHelper.capNhatLaiIDCauHoi();
            // Lấy danh sách câu hỏi từ CSDL dựa trên chuDeID
            cauHoiList = databaseHelper.getDanhSachCauHoiByChuDeId(chuDeID);

            // Khởi tạo adapter và set cho ListView
            adapter = new CauHoiAdapter(ThemCauHoi.this, R.layout.dong_cauhoi, cauHoiList);
            lvCauHoi.setAdapter(adapter);
            lvCauHoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    int n = position +1;
                    Intent intent = new Intent(ThemCauHoi.this, ChiTietCauHoi.class);
                    intent.putExtra("n", n);
                    intent.putExtra("idUser",idUser);
                    startActivity(intent);
                }
            });
        } else {

        }

    }

}

