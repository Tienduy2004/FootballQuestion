package com.example.myapplication;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChiTietChuDe extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    EditText edtChuDe;
    Button Sua,Xoa,QuayLai;
    private List<CauHoi> cauHoiList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_chu_de);
        databaseHelper = new DatabaseHelper(this);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setControl();
        Event();
    }
    public void setControl(){
        edtChuDe = findViewById(R.id.edtctChuDe);
        Sua = findViewById(R.id.btnSuaCD);
        Xoa = findViewById(R.id.btnXoaChuDe);
        QuayLai = findViewById(R.id.btnQuayLauDSCD);
    }
    public void Event(){
        Intent intent = getIntent();
        int idChuDe = intent.getIntExtra("idchude",-1);
        String tenChuDe = intent.getStringExtra("tenchude");
        edtChuDe.setText(tenChuDe);
        if (cauHoiList == null) {
            cauHoiList = new ArrayList<>();
        }
        databaseHelper.capNhatLaiIDCauHoi();
        // Lấy danh sách câu hỏi từ CSDL dựa trên chuDeID
        cauHoiList = databaseHelper.getDanhSachCauHoiByChuDeId(idChuDe);
        Sua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            databaseHelper.suaChuDe(edtChuDe.getText().toString(),idChuDe);
                Toast.makeText(ChiTietChuDe.this,"Sửa thành công",Toast.LENGTH_SHORT).show();
            updateChuDeList();
            updateChuDeListDS();
            finish();
            }
        });
        Xoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cauHoiList.size() == 0){
                    databaseHelper.XoaChuDe(idChuDe);
                    updateChuDeList();
                    updateChuDeListDS();
                    finish();
                }
                else {
                    Toast.makeText(ChiTietChuDe.this,"Hãy xóa câu hỏi trong chủ đề",Toast.LENGTH_SHORT).show();
                }
            }
        });
        QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              finish();
            }
        });
    }
    private void updateChuDeList() {
        List<ChuDe> updatedList = databaseHelper.getAllChuDe();
        ChuDe1.getChuDe().clear();
        ChuDe1.getChuDe().addAll(updatedList);
        ChuDe1.adapter.notifyDataSetChanged();
    }
    private void updateChuDeListDS() {
        List<ChuDe> updatedList = databaseHelper.getAllChuDe();
        DanhSachChuDe.getChuDe().clear();
        DanhSachChuDe.getChuDe().addAll(updatedList);
        DanhSachChuDe.chuDeAdater.notifyDataSetChanged();
    }
}